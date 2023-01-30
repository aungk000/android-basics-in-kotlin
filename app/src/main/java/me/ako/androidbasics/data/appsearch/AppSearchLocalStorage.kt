package me.ako.androidbasics.data.appsearch

import android.content.Context
import android.util.Log
import androidx.appsearch.app.AppSearchBatchResult
import androidx.appsearch.app.AppSearchSession
import androidx.appsearch.app.GenericDocument
import androidx.appsearch.app.PutDocumentsRequest
import androidx.appsearch.app.RemoveByDocumentIdRequest
import androidx.appsearch.app.SearchResults
import androidx.appsearch.app.SearchSpec
import androidx.appsearch.app.SetSchemaRequest
import androidx.appsearch.exceptions.AppSearchException
import androidx.appsearch.localstorage.LocalStorage
import com.google.common.util.concurrent.FutureCallback
import com.google.common.util.concurrent.Futures
import com.google.common.util.concurrent.ListenableFuture

class AppSearchLocalStorage(context: Context) {
    init {
        val sessionFuture = LocalStorage.createSearchSessionAsync(
            LocalStorage.SearchContext.Builder(context, "search_db").build()
        )
        val setSchemaRequest = SetSchemaRequest.Builder()
            .addDocumentClasses(ActivityDocument::class.java)
            .build()
        val setSchemaFuture = Futures.transformAsync(
            sessionFuture,
            {
                it.setSchemaAsync(setSchemaRequest)
            },
            {

            }
        )

        val activity = ActivityDocument(
            namespace = "user1",
            id = "userId",
            score = 10,
            text = "Buy fresh fruit"
        )

        val putRequest = PutDocumentsRequest.Builder().addDocuments(activity).build()
        val putFuture = Futures.transformAsync(
            sessionFuture,
            {
                it.putAsync(putRequest)
            },
            {

            }
        )

        Futures.addCallback(putFuture, object : FutureCallback<AppSearchBatchResult<String, Void>> {
            override fun onSuccess(result: AppSearchBatchResult<String, Void>?) {
                // Gets map of successful results from Id to Void
                val successResults = result?.successes
                // Gets map of failed results from Id to AppSearchResult
                val failureResults = result?.failures
            }

            override fun onFailure(t: Throwable) {
                Log.e("AppSearchLocalStorage", "Failed to put documents.", t)
            }
        }
        ) {

        }

        val searchSpec = SearchSpec.Builder()
            .addFilterNamespaces("user1")
            .build()
        val searchFuture = Futures.transform(
            sessionFuture,
            {
                it.search("fruit", searchSpec)
            },
            {

            }
        )

        Futures.addCallback(searchFuture, object : FutureCallback<SearchResults> {
            override fun onSuccess(result: SearchResults?) {
                // iterateSearchResults
            }

            override fun onFailure(t: Throwable) {
                Log.e("AppSearchLocalStorage", "Failed to search in AppSearch.", t)
            }
        }) {

        }
    }

    fun closeSession(sessionFuture: ListenableFuture<AppSearchSession>) {
        val closeFuture = Futures.transform(
            sessionFuture,
            {
                it.close()
            }
        ) {

        }
    }

    fun persistDisk(sessionFuture: ListenableFuture<AppSearchSession>) {
        val requestFlushFuture = Futures.transformAsync(
            sessionFuture,
            {
                it.requestFlushAsync()
            }
        ) {

        }

        Futures.addCallback(
            requestFlushFuture,
            object : FutureCallback<Void> {
                override fun onSuccess(result: Void?) {
                    // Success! Database updates have been persisted to disk.
                }

                override fun onFailure(t: Throwable) {
                    Log.e("AppSearchLocalStorage", "Failed to flush database updates.", t)
                }
            }) {
        }
    }

    fun removeDocument(sessionFuture: ListenableFuture<AppSearchSession>) {
        val removeRequest = RemoveByDocumentIdRequest.Builder("user1")
            .addIds("userId")
            .build()

        val removeFuture = Futures.transformAsync(
            sessionFuture,
            {
                it.removeAsync(removeRequest)
            }
        ) {

        }
    }

    fun iterateSearchResults(result: SearchResults) {
        Futures.transform(
            result.nextPageAsync,
            {
                // Gets GenericDocument from SearchResult.
                val genericDocument: GenericDocument = it[0].genericDocument
                val schemaType = genericDocument.schemaType
                val activityDocument: ActivityDocument? = try {
                    if (schemaType == "ActivityDocument") {
                        // Converts GenericDocument object to object.
                        genericDocument.toDocumentClass(ActivityDocument::class.java)
                    } else null
                } catch (e: AppSearchException) {
                    Log.e("AppSearchLocalStorage", "iterateSearchResults: ", e)
                    null
                }
                activityDocument
            },
            {

            }
        )
    }
}