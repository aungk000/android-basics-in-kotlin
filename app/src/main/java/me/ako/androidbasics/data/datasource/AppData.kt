package me.ako.androidbasics.data.datasource

import me.ako.androidbasics.R
import me.ako.androidbasics.data.model.*
import org.joda.time.DateTime

class AppData {
    val pathways = listOf(
        PathwayEntity(
            1,
            1,
            1,
            R.drawable.badge_intro_to_kotlin,
            5,
            "Introduction to Kotlin",
            "Learn to code in Kotlin, a modern programming language that makes coding " +
                    "clear and accessible.",
            DateTime.now()
        ),
        PathwayEntity(
            2,
            1,
            2,
            R.drawable.badge_first_app_in_android_studio,
            6,
            "Create your first app",
            "Learn to create Android apps using Android Studio in this introductory PathwayEntity.",
            DateTime.now()
        ),
        PathwayEntity(
            3,
            1,
            3,
            R.drawable.badge_build_a_basic_layout,
            4,
            "Build a basic layout",
            "Learn how to add images and text to your Android apps.",
            DateTime.now()
        ),
        PathwayEntity(
            4,
            1,
            4,
            R.drawable.badge_dice_roller_app,
            9,
            "Add a button to an app",
            "Learn how to use classes, objects, and conditionals to create an interactive " +
                    "app for your users.",
            DateTime.now()
        ),
        PathwayEntity(
            5,
            2,
            1,
            R.drawable.badge_get_user_input_in_an_app_part_1,
            6,
            "Get user input in an app: Part 1",
            "Create a tip calculator app by building the layout first, then implement the " +
                    "logic to calculate the tip from the user input.",
            DateTime.now()
        ),
        PathwayEntity(
            6,
            2,
            2,
            R.drawable.badge_get_user_input_in_an_app_part_2,
            5,
            "Get user input in an app: Part 2",
            "Add visual polish to the Tip Calculator app to create a better user experience.",
            DateTime.now()
        ),
        PathwayEntity(
            7,
            2,
            3,
            R.drawable.badge_display_a_scrollable_list,
            7,
            "Display a scrollable list",
            "Create an app that displays a scrollable list of inspiring text and images " +
                    "using the RecyclerView widget in Android. Along the way, you’ll learn about " +
                    "using lists in Kotlin to store a collection of data.",
            DateTime.now()
        ),
        PathwayEntity(
            8,
            3,
            1,
            R.drawable.badge_navigate_between_screens,
            5,
            "Navigate between screens",
            "Add another screen to an app by adding a second activity, and use an intent " +
                    "to navigate to it. Also, learn the basics of the activity lifecycle as you " +
                    "navigate into and out of different activities.",
            DateTime.now()
        ),
        PathwayEntity(
            9,
            3,
            2,
            R.drawable.badge_introduction_to_the_navigation_component,
            5,
            "Introduction to the Navigation component",
            "Learn how to simplify in-app screen navigation with the Navigation component, " +
                    "a collection of libraries, useful tooling and best practices.",
            DateTime.now()
        ),
        PathwayEntity(
            10,
            3,
            3,
            R.drawable.badge_architecture_components,
            4,
            "Architecture components",
            "Learn how to use Android Jetpack Architecture components, a collection of " +
                    "libraries that help you design robust, testable, and maintainable apps.",
            DateTime.now()
        ),
        PathwayEntity(
            11,
            3,
            4,
            R.drawable.badge_advanced_navigation_app_examples,
            5,
            "Advanced navigation app examples",
            "Combine everything you've learned in this unit about navigation, view model, " +
                    "data binding, and live data by building a more advanced app that also includes " +
                    "custom back stack behavior.",
            DateTime.now()
        ),
        PathwayEntity(
            12,
            3,
            5,
            R.drawable.badge_adaptive_layouts,
            4,
            "Adaptive layouts",
            "Learn how to make apps adaptable to different screen sizes.",
            DateTime.now()
        ),
        PathwayEntity(
            13,
            4,
            1,
            R.drawable.badge_coroutines,
            3,
            "Coroutines",
            "Write code for more advanced and complex applications.",
            DateTime.now()
        ),
        PathwayEntity(
            14,
            4,
            2,
            R.drawable.badge_get_and_display_data_from_the_internet,
            6,
            "Get and display data from the internet",
            "Retrieve and display images over the internet with HTTP/REST.",
            DateTime.now()
        ),
        PathwayEntity(
            15,
            5,
            1,
            R.drawable.badge_introduction_to_sql_room_and_flow,
            3,
            "Introduction to SQL, Room, and Flow",
            "Learn the basics of reading and manipulating data with SQL, and how to " +
                    "create and use relational databases in an Android app with the Room library.",
            DateTime.now()
        ),
        PathwayEntity(
            16,
            5,
            2,
            R.drawable.badge_save_changes_in_your_app,
            7,
            "Save changes in your app",
            "Learn how to save user's data in the app. First you'll use Room to read and " +
                    "write changes to a database. Then you'll use Preferences DataStore to store " +
                    "user preferences in the app.",
            DateTime.now()
        ),
        PathwayEntity(
            17,
            6,
            1,
            R.drawable.badge_schedule_tasks_with_workmanager,
            5,
            "Schedule tasks with WorkManager",
            "Learn when and how to use WorkManager, an API that handles background work " +
                    "that needs to run regardless of whether the application process is still running.",
            DateTime.now()
        )
    )

    val units = listOf(
        UnitEntity(
            1,
            "Unit 1: Kotlin basics",
            "Take your first steps programming in Kotlin, add images and text to " +
                    "your Android apps, and learn how to use classes, objects, and conditionals " +
                    "to create an interactive app for your users."
        ),
        UnitEntity(
            2,
            "Unit 2: Layouts",
            "Build two different apps, and improve the user interface of your app by " +
                    "learning about layouts, Material Design guidelines, and best practices for " +
                    "UI development"
        ),
        UnitEntity(
            3,
            "Unit 3: Navigation",
            "Enhance your user's ability to navigate across, into and back out from " +
                    "the various screens within your app for a consistent and predictable user " +
                    "experience."
        ),
        UnitEntity(
            4,
            "Unit 4: Connect to the internet",
            "Write coroutines for complex code, and learn about HTTP and REST to get " +
                    "data from the internet. Then, use the Coil library to display images in " +
                    "your app."
        ),
        UnitEntity(
            5,
            "Unit 5: Data persistence",
            "Keep your apps working through any disruptions to essential networks or " +
                    "processes for a smooth and consistent user experience."
        ),
        UnitEntity(
            6,
            "Unit 6: WorkManager",
            "Use Android Jetpack’s WorkManager API to schedule necessary background " +
                    "work, like backing up data or downloading fresh content, that keeps " +
                    "running even if the app exits or the device restarts."
        )
    )

    val activities = listOf(
        ActivityEntity(
            1,
            1,
            1,
            "Welcome to Android Basics in Kotlin",
            "Meet the team and learn what you’ll need to begin developing Android apps " +
                    "in Kotlin.",
            ActivityType.Video,
            true,
            "https://www.youtube.com/watch?v=OpQ3VzzgE0g"
        ),
        ActivityEntity(
            2,
            1,
            2,
            "Build your first Android app in Kotlin",
            "Learn more about the course goals and requirements in this introduction to " +
                    "Unit 1. To check if your computer can download Android Studio, see the system " +
                    "requirements at the bottom of this page: https://developer.android.com/studio",
            ActivityType.Video,
            true,
            "https://www.youtube.com/watch?v=7VG8b7FtTo0"
        ),
        ActivityEntity(
            3,
            1,
            3,
            "Write your first program in Kotlin",
            "Create your first program in Kotlin.",
            progress = 35,
            url = "https://developer.android.com/codelabs/basic-android-kotlin-training-first-kotlin-program"
        ),
        ActivityEntity(
            4,
            1,
            4,
            "Create a birthday message in Kotlin",
            "Create a short program in Kotlin using functions and loops to print a happy " +
                    "birthday message.",
            progress = 35,
            url = "https://developer.android.com/codelabs/basic-android-kotlin-training-kotlin-birthday-message"
        ),
        ActivityEntity(
            5,
            2,
            1,
            "Introduction to Android Studio",
            "Learn about Android Studio and how you can use it to build apps. You can " +
                    "find the official documentation at https://developer.android.com/studio/intro",
            ActivityType.Video,
            true,
            "https://www.youtube.com/watch?v=NCoekcDxbrI"
        ),
        ActivityEntity(
            6,
            2,
            2,
            "Download and install Android Studio",
            "Download and install Android Studio, Google's Android development " +
                    "environment.",
            progress = 25,
            url = "https://developer.android.com/codelabs/basic-android-kotlin-training-install-android-studio"
        ),
        ActivityEntity(
            7,
            2,
            3,
            "Create and run your first Android app",
            "Build your first Android app in Kotlin using Android Studio.",
            progress = 25,
            url = "https://developer.android.com/codelabs/basic-android-kotlin-training-first-template-project"
        ),
        ActivityEntity(
            8,
            2,
            4,
            "Run your app on a mobile device",
            "Learn how to set up an Android device and run your Android app on it.",
            optional = true,
            url = "https://developer.android.com/codelabs/basic-android-kotlin-training-run-on-mobile-device"
        ),
        ActivityEntity(
            9,
            2,
            5,
            "Learn the basics of Android tests",
            "Learn about automated tests in Android and where to find them within an " +
                    "Android project.",
            progress = 25,
            url = "https://developer.android.com/codelabs/android-basics-kotlin-testing-basics"
        ),
        ActivityEntity(
            10,
            3,
            1,
            "Design a Birthday Card app",
            "Learn about the tools you’ll use to start developing apps.",
            ActivityType.Video,
            true,
            "https://www.youtube.com/watch?v=2VfD8QC0Q7M"
        ),
        ActivityEntity(
            11,
            3,
            2,
            "Create a Birthday Card app",
            "Create an Android app that displays text, using the Layout Editor in " +
                    "Android Studio.",
            progress = 35,
            url = "https://developer.android.com/codelabs/basic-android-kotlin-training-birthday-card-app"
        ),
        ActivityEntity(
            12,
            3,
            3,
            "Add images to your Android app",
            "Learn how to add and display images in your app using an ImageView.",
            progress = 35,
            url = "https://developer.android.com/codelabs/basic-android-kotlin-training-birthday-card-app-image"
        ),
        ActivityEntity(
            13,
            4,
            1,
            "Classes and object instances in Kotlin",
            "Create a Kotlin program that generates random numbers to simulate rolling dice.",
            progress = 10,
            url = "https://developer.android.com/codelabs/basic-android-kotlin-training-create-dice-roller-in-kotlin"
        ),
        ActivityEntity(
            14,
            4,
            2,
            "Create an interactive Dice Roller app",
            "Learn how to add a button and modify the activity code of an Android app.",
            progress = 10,
            url = "https://developer.android.com/codelabs/basic-android-kotlin-training-create-dice-roller-app-with-button"
        ),
        ActivityEntity(
            15,
            4,
            3,
            "Add conditional behavior in Kotlin",
            "Create a Kotlin program that uses conditionals to compare a user's dice roll " +
                    "with a given lucky number.",
            progress = 10,
            url = "https://developer.android.com/codelabs/basic-android-kotlin-training-conditional-dice-roll-behavior"
        ),
        ActivityEntity(
            16,
            4,
            4,
            "Add images to the Dice Roller app",
            "Enhance the user experience by adding images to the Dice Roller app.",
            progress = 10,
            url = "https://developer.android.com/codelabs/basic-android-kotlin-training-dice-roller-images"
        ),
        ActivityEntity(
            17,
            4,
            5,
            "Write unit tests",
            "Learn why testing is important, what unit tests look like, how to write unit " +
                    "tests, and how to run them.",
            progress = 10,
            url = "https://developer.android.com/codelabs/android-basics-kotlin-write-unit-tests"
        ),
        ActivityEntity(
            18,
            4,
            6,
            "Intro to debugging",
            "Learn to read stack traces and familiarize yourself with the debugging tools " +
                    "in Android Studio.",
            progress = 10,
            url = "https://developer.android.com/codelabs/basic-android-kotlin-training-intro-debugging"
        ),
        ActivityEntity(
            19,
            4,
            7,
            "What can you build with these basics?",
            "Learn where you can go from here!",
            ActivityType.Video,
            true,
            "https://www.youtube.com/watch?v=tHFJkps7U5M"
        ),
        ActivityEntity(
            20,
            5,
            1,
            "Welcome to Unit 2: Layouts",
            "Learn about the two apps you’ll build in this unit to advance your knowledge " +
                    "about UI layouts in Android.",
            ActivityType.Video,
            true,
            "https://www.youtube.com/watch?v=JJLNvVXPYYw"
        ),
        ActivityEntity(
            21,
            5,
            2,
            "Tip calculator app introduction",
            "Get a glimpse of the tip calculator app you’ll be building in this pathway.",
            ActivityType.Video,
            true,
            "https://www.youtube.com/watch?v=a-Ss24ZH3WU"
        ),
        ActivityEntity(
            22,
            5,
            3,
            "Classes and inheritance in Kotlin",
            "To build more complex apps, you’ll need to better understand how classes and " +
                    "inheritance work so that you can more fully use what the Android platform offers.",
            url = "https://developer.android.com/codelabs/basic-android-kotlin-training-classes-and-inheritance",
            progress = 25
        ),
        ActivityEntity(
            23,
            5,
            4,
            "Create XML layouts for Android",
            "Learn about new UI components, such as editable text fields, radio buttons, " +
                    "and switches to build up the layout for your tip calculator app. Instead of " +
                    "using the Layout Editor in Android Studio, you’ll be editing the layout for " +
                    "your app in XML.",
            url = "https://developer.android.com/codelabs/basic-android-kotlin-training-xml-layouts",
            progress = 25
        ),
        ActivityEntity(
            24,
            5,
            5,
            "Calculate the tip",
            "Learn how to write Kotlin code to interact with the UI elements in the tip " +
                    "calculator app, so that you can calculate the tip. You’ll also learn how to " +
                    "handle edge cases in your app to make your app more robust.",
            url = "https://developer.android.com/codelabs/basic-android-kotlin-training-tip-calculator",
            progress = 25
        ),
        ActivityEntity(
            25,
            6,
            1,
            "Change the app theme",
            "Choose colors for your app and apply them consistently throughout your app " +
                    "using themes.",
            url = "https://developer.android.com/codelabs/basic-android-kotlin-training-change-app-theme",
            progress = 20
        ),
        ActivityEntity(
            26,
            6,
            2,
            "Change the app icon",
            "Change the launcher icon for your app with the help of the Image Asset Studio " +
                    "tool in Android Studio.",
            url = "https://developer.android.com/codelabs/basic-android-kotlin-training-change-app-icon",
            progress = 20
        ),
        ActivityEntity(
            27,
            6,
            3,
            "Create a more polished user experience",
            "Update your tip calculator app to look more professional and polished by " +
                    "following Material Design guidelines and best practices for UI development.",
            url = "https://developer.android.com/codelabs/basic-android-kotlin-training-polished-user-experience",
            progress = 20
        ),
        ActivityEntity(
            28,
            6,
            4,
            "Write Instrumentation Tests",
            "Learn what instrumentation tests look like, how to write instrumentations " +
                    "tests, and how to run them.",
            url = "https://developer.android.com/codelabs/android-basics-kotlin-write-instrumentation-tests",
            progress = 20
        ),
        ActivityEntity(
            29,
            7,
            1,
            "Affirmations app introduction",
            "Get an introduction to the Affirmations app you will be creating in this pathway.",
            ActivityType.Video,
            true,
            "https://www.youtube.com/watch?v=VCeEIkT_2U4",
            progress = 15
        ),
        ActivityEntity(
            30,
            7,
            2,
            "Use Lists in Kotlin",
            "Learn how to create lists in Kotlin and loop through them.",
            url = "https://developer.android.com/codelabs/basic-android-kotlin-training-lists",
            progress = 15
        ),
        ActivityEntity(
            31,
            7,
            3,
            "Use RecyclerView to display a scrollable list",
            "Learn how to efficiently display a list of text in a RecyclerView and " +
                    "understand its architecture.",
            url = "https://developer.android.com/codelabs/basic-android-kotlin-training-recyclerview-scrollable-list",
            progress = 15
        ),
        ActivityEntity(
            32,
            7,
            4,
            "Display a list of images using cards",
            "Learn how to add images to our scrolling list of affirmations. We’ll also " +
                    "enhance the look of our app’s UI by using MaterialCardView and fine-tuning " +
                    "its theme colors.",
            url = "https://developer.android.com/codelabs/basic-android-kotlin-training-display-list-cards",
            progress = 15
        ),
        ActivityEntity(
            33,
            7,
            5,
            "Test Lists and Adapters",
            "Learn a little bit more about best practices for testing and how to add " +
                    "testing dependencies. Get more practice writing both unit and instrumentation tests.",
            url = "https://developer.android.com/codelabs/android-basics-kotlin-affirmations-test-lists-and-adapters",
            progress = 15
        ),
        ActivityEntity(
            34,
            7,
            6,
            "Project: Dogglers app",
            "Use your knowledge of layouts to build a scrollable dog photo app in Android " +
                    "Studio, and run tests against your code to make sure everything works as expected.",
            url = "https://developer.android.com/codelabs/basic-android-kotlin-training-project-dogglers-app",
            progress = 15
        ),
        ActivityEntity(
            35,
            8,
            1,
            "Welcome to Unit 3: Navigation",
            "Learn the basic concepts of app navigation, the interactions that allow users " +
                    "to navigate across, into, and back out from the different pieces of content within an app.",
            ActivityType.Video,
            true,
            "https://www.youtube.com/watch?v=SdfryvVkBBc"
        ),
        ActivityEntity(
            36,
            8,
            2,
            "Collections in Kotlin",
            "Understand collections and how to manipulate them using lambdas and higher-order functions.",
            url = "https://developer.android.com/codelabs/basic-android-kotlin-training-collections",
            progress = 25
        ),
        ActivityEntity(
            37,
            8,
            3,
            "Activities and intents",
            "Build a Words app with multiple activities that uses intents to navigate " +
                    "between them and also passes data to other apps.",
            url = "https://developer.android.com/codelabs/basic-android-kotlin-training-activities-intents",
            progress = 25
        ),
        ActivityEntity(
            38,
            8,
            4,
            "Stages of the activity lifecycle",
            "Learn about the activity lifecycle and the different states of an activity, " +
                    "from first initialized to finally destroyed.",
            url = "https://developer.android.com/codelabs/basic-android-kotlin-training-activity-lifecycle",
            progress = 25
        ),
        ActivityEntity(
            39,
            9,
            1,
            "Android Jetpack: Introducing Navigation Component",
            "Learn about the Navigation Architecture Component in Android Jetpack, which " +
                    "provides a framework for building in-app navigation.",
            ActivityType.Video,
            false,
            "https://www.youtube.com/watch?v=Y0Cs2MQxyIs",
            progress = 25
        ),
        ActivityEntity(
            40,
            9,
            2,
            "Fragments and the Navigation component",
            "Understand how to use fragments and implement navigation within a single activity.",
            url = "https://developer.android.com/codelabs/basic-android-kotlin-training-fragments-navigation-component",
            progress = 25
        ),
        ActivityEntity(
            41,
            9,
            3,
            "Test Navigation Components",
            "Learn how to test navigation handled by Navigation Components and how to " +
                    "mitigate repeat code in tests.",
            url = "https://developer.android.com/codelabs/android-basics-kotlin-test-navigation-components",
            progress = 25
        ),
        ActivityEntity(
            42,
            9,
            4,
            "Navigation: Overview - MAD Skills",
            "Learn Modern Android Development (MAD) skills with the Navigation component, " +
                    "which is both a tool and an API for editing the Navigation flows inside of your application.",
            ActivityType.Video,
            true,
            "https://www.youtube.com/watch?v=xITkfPIaStU"
        ),
        ActivityEntity(
            43,
            10,
            1,
            "Unscramble app introduction",
            "Build a game app that asks you to try your luck at unscrambling words.",
            ActivityType.Video,
            true,
            "https://www.youtube.com/watch?v=Xi0649S7evA"
        ),
        ActivityEntity(
            44,
            10,
            2,
            "Store data in ViewModel",
            "Improve your app by implementing a ViewModel to retain app data during " +
                    "configuration changes.",
            url = "https://developer.android.com/codelabs/basic-android-kotlin-training-viewmodel",
            progress = 35
        ),
        ActivityEntity(
            45,
            10,
            3,
            "Use LiveData with ViewModel",
            "Convert the app data in the ViewModel to LiveData and observe the changes " +
                    "to update the UI automatically.",
            url = "https://developer.android.com/codelabs/basic-android-kotlin-training-livedata",
            progress = 35
        ),
        ActivityEntity(
            46,
            11,
            1,
            "Cupcake app introduction",
            "Get an introduction to the Cupcake app you will be creating in this pathway.",
            ActivityType.Video,
            true,
            "https://www.youtube.com/watch?v=R-7Xbkhohmo"
        ),
        ActivityEntity(
            47,
            11,
            2,
            "Shared ViewModel",
            "Build a cupcake ordering app, and use a shared ViewModel to share data " +
                    "between the fragments of the same activity.",
            url = "https://developer.android.com/codelabs/basic-android-kotlin-training-shared-viewmodel",
            progress = 25
        ),
        ActivityEntity(
            48,
            11,
            3,
            "Navigation and the backstack",
            "Manipulate the back stack in a custom way by modifying the Cupcake app so " +
                    "that the user can cancel a cupcake order.",
            url = "https://developer.android.com/codelabs/basic-android-kotlin-training-navigation-backstack",
            progress = 25
        ),
        ActivityEntity(
            49,
            11,
            4,
            "Test ViewModels and LiveData",
            "Learn how to write unit tests for ViewModels and LiveData.",
            url = "https://developer.android.com/codelabs/android-basics-kotlin-test-viewmodel-and-livedata",
            progress = 25
        ),
        ActivityEntity(
            50,
            12,
            1,
            "Create a two pane layout",
            "Learn about using the SlidingPaneLayout component to adapt layouts to " +
                    "different screen sizes.",
            optional = true,
            url = "https://developer.android.com/"
        ),
        ActivityEntity(
            51,
            12,
            2,
            "Adaptive layouts",
            "In this codelab, you learn how to use SlidingPaneLayout to your app " +
                    "adaptable to different screen sizes.",
            url = "https://developer.android.com/codelabs/basic-android-kotlin-training-adaptive-layouts",
            progress = 35
        ),
        ActivityEntity(
            52,
            12,
            3,
            "Migrate your UI to responsive layouts",
            "Learn about the Responsive UI principles of flexibility and continuity.",
            ActivityType.Article,
            true,
            "https://developer.android.com/guide/topics/large-screens/migrate-to-responsive-layouts"
        ),
        ActivityEntity(
            53,
            12,
            4,
            "Project: Lunch Tray app",
            "Apply what you've learned in this unit to complete a food ordering app by " +
                    "implementing a ViewModel with data binding, and adding navigation between fragments.",
            url = "https://developer.android.com/codelabs/basic-android-kotlin-training-project-lunch-tray",
            progress = 35
        ),
        ActivityEntity(
            54,
            13,
            1,
            "Welcome to Unit 4: Connect to the internet",
            "In this unit, you’ll learn how to build an app that can get data from the internet.",
            ActivityType.Video,
            true,
            "https://www.youtube.com/watch?v=M9djOLAwnPc"
        ),
        ActivityEntity(
            55,
            13,
            2,
            "Introduction to coroutines",
            "Learn to write clear, non-blocking code while building more complex and advanced apps.",
            url = "https://developer.android.com/codelabs/basic-android-kotlin-training-introduction-coroutines",
            progress = 50
        ),
        ActivityEntity(
            56,
            14,
            1,
            "Introduction to HTTP/REST",
            "Learn how apps communicate with each other over the internet.",
            ActivityType.Video,
            url = "https://www.youtube.com/watch?v=uRwQKikomtE",
            progress = 15
        ),
        ActivityEntity(
            57,
            14,
            2,
            "Get data from the internet",
            "Connect your app to backend servers using the third-party library Retrofit, " +
                    "and learn about REST web services.",
            url = "https://developer.android.com/codelabs/basic-android-kotlin-training-getting-data-internet",
            progress = 15
        ),
        ActivityEntity(
            58,
            14,
            3,
            "Load and display images from the internet",
            "Load and display images from a web URL using the Coil library.",
            url = "https://developer.android.com/codelabs/basic-android-kotlin-training-internet-images",
            progress = 15
        ),
        ActivityEntity(
            59,
            14,
            4,
            "Debug with breakpoints",
            "Learn how to use breakpoints and watch specific variables when debugging.",
            url = "https://developer.android.com/codelabs/basic-android-kotlin-training-debugging-with-breakpoints",
            progress = 15
        ),
        ActivityEntity(
            60,
            14,
            5,
            "Project: Amphibians app",
            "Take an app that displays information about different amphibian species, and " +
                    "use your knowledge of networking, JSON parsing, and view models to enable the " +
                    "app to use data from the network. The app will get its data from a custom API " +
                    "for this project and display it in a list view.",
            url = "https://developer.android.com/codelabs/basic-android-kotlin-training-project-amphibians",
            progress = 15
        ),
        ActivityEntity(
            61,
            15,
            1,
            "SQL Basics",
            "Learn the fundamentals of relational databases and practice running SQL " +
                    "queries with Database Inspector in Android Studio.",
            url = "https://developer.android.com/codelabs/basic-android-kotlin-training-sql-basics",
            progress = 35
        ),
        ActivityEntity(
            62,
            15,
            2,
            "Introduction to Room and Flow",
            "Learn to work with databases on Android using the Room persistence library.",
            url = "https://developer.android.com/codelabs/basic-android-kotlin-training-intro-room-flow",
            progress = 35
        ),
        ActivityEntity(
            63,
            16,
            1,
            "Using Room Kotlin APIs",
            "Learn how to use and test Room Kotlin APIs.",
            ActivityType.Video,
            true,
            "https://www.youtube.com/watch?v=vsDkhRTMdA0"
        ),
        ActivityEntity(
            64,
            16,
            2,
            "Persist data with Room",
            "Learn to build an app that uses Room to save inventory items into a SQLite database.",
            url = "https://developer.android.com/codelabs/basic-android-kotlin-training-persisting-data-room",
            progress = 15
        ),
        ActivityEntity(
            65,
            16,
            3,
            "Read and update data with Room",
            "Learn how to read, display, update, and delete data from the app’s SQLite " +
                    "database using Room.",
            url = "https://developer.android.com/codelabs/basic-android-kotlin-training-update-data-room",
            progress = 15
        ),
        ActivityEntity(
            66,
            16,
            4,
            "Repository Pattern",
            "Implement caching in an existing app using the repository pattern.",
            url = "https://developer.android.com/codelabs/basic-android-kotlin-training-repository-pattern",
            progress = 15
        ),
        ActivityEntity(
            67,
            16,
            5,
            "Preferences DataStore",
            "Learn how to use a data storage solution called Jetpack DataStore to store a " +
                    "key-value pair in your application.",
            url = "https://developer.android.com/codelabs/basic-android-kotlin-training-preferences-datastore",
            progress = 15
        ),
        ActivityEntity(
            68,
            16,
            6,
            "Project: Forage app",
            "The completed Forage app allows users to keep track of items, food for example, " +
                    "that they've foraged for in nature. This data is persisted between sessions " +
                    "using Room. You'll use your knowledge of Room and performing read, write, update, " +
                    "and delete operations on a database to implement persistence in the Forage app.",
            url = "https://developer.android.com/codelabs/basic-android-kotlin-training-project-forage",
            progress = 15
        ),
        ActivityEntity(
            69,
            17,
            1,
            "Introduction to WorkManager basics",
            "Learn the basics of using WorkManager in this introductory video for Unit 6.",
            ActivityType.Video,
            true,
            "https://www.youtube.com/watch?v=YOZCm2Qk_4o"
        ),
        ActivityEntity(
            70,
            17,
            2,
            "Introduction to WorkManager implementation",
            "Learn to use WorkRequests and get familiar with its classes and functions.",
            ActivityType.Video,
            true,
            "https://www.youtube.com/watch?v=UOoDt1El1f4"
        ),
        ActivityEntity(
            71,
            17,
            3,
            "Background Work with WorkManager",
            "Learn to use WorkManager to write a simple job, then perform more complex " +
                    "chained jobs with constraints.",
            url = "https://developer.android.com/codelabs/android-workmanager",
            progress = 35
        ),
        ActivityEntity(
            72,
            17,
            4,
            "Project: Water Me! app",
            "The Water Me! app consists of a list of plants, some information about them, " +
                    "and a description for how often each one should be watered. For each of these " +
                    "plants, the completed app will schedule a reminder for when they should be watered.",
            url = "https://developer.android.com/codelabs/basic-android-kotlin-training-project-water-me",
            progress = 35
        ),
        ActivityEntity(
            pathwayId = 1,
            number = 5,
            title = "Quiz",
            description = "Test what you’ve learned and earn your Introduction to Kotlin badge.",
            type = ActivityType.Quiz,
            url = "https://developer.android.com/courses/quizzes/android-basics-kotlin-one/android-basics-kotlin-pathway-one",
            progress = 30
        ),
        ActivityEntity(
            pathwayId = 2,
            number = 6,
            title = "Quiz",
            description = "Test your knowledge of Android Studio, and earn your Android Studio badge.",
            type = ActivityType.Quiz,
            url = "https://developer.android.com/courses/quizzes/android-basics-kotlin-two/android-basics-kotlin-pathway-two",
            progress = 25
        ),
        ActivityEntity(
            pathwayId = 3,
            number = 4,
            title = "Quiz",
            description = "Test your knowledge of basic layouts, and earn your Basic Layouts badge.",
            type = ActivityType.Quiz,
            url = "https://developer.android.com/courses/quizzes/android-basics-kotlin-three/android-basics-kotlin-pathway-three",
            progress = 30
        ),
        ActivityEntity(
            pathwayId = 4,
            number = 8,
            title = "Project: Lemonade app",
            description = "Use what you've learned in this unit to build a new app in Android Studio, " +
                    "and run a test suite to see that your code works as expected.",
            type = ActivityType.CodeLab,
            url = "https://developer.android.com/codelabs/basic-android-kotlin-training-project-lemonade",
            progress = 20
        ),
        ActivityEntity(
            pathwayId = 4,
            number = 9,
            title = "Quiz",
            description = "Test your knowledge of classes, objects, and conditionals in Kotlin, " +
                    "and earn your Dice Roller badge.",
            type = ActivityType.Quiz,
            url = "https://developer.android.com/courses/quizzes/android-basics-kotlin-four/android-basics-kotlin-pathway-four",
            progress = 20
        ),
        ActivityEntity(
            pathwayId = 5,
            number = 6,
            title = "Quiz",
            description = "Test your knowledge of class inheritance and layouts in XML, and earn " +
                    "your Get user input in an app: Part 1 badge.",
            type = ActivityType.Quiz,
            url = "https://developer.android.com/courses/quizzes/android-basics-kotlin-unit-2-pathway-1/android-basics-kotlin-unit-2-pathway-1",
            progress = 25
        ),
        ActivityEntity(
            pathwayId = 6,
            number = 6,
            title = "Quiz",
            description = "Test your knowledge of the Gradle build system, app icons, and themes, " +
                    "and earn your Get user input in an app: Part 2 badge.",
            type = ActivityType.Quiz,
            url = "https://developer.android.com/courses/quizzes/android-basics-kotlin-unit-2-pathway-2/android-basics-kotlin-unit-2-pathway-2",
            progress = 20
        ),
        ActivityEntity(
            pathwayId = 7,
            number = 7,
            title = "Quiz",
            description = "Test your knowledge of lists in Kotlin and Android’s RecyclerView widget " +
                    "to earn your Display a scrollable list badge.",
            type = ActivityType.Quiz,
            url = "https://developer.android.com/courses/quizzes/android-basics-kotlin-unit-2-pathway-3/android-basics-kotlin-unit-2-pathway-3",
            progress = 10
        ),
        ActivityEntity(
            pathwayId = 8,
            number = 5,
            title = "Quiz",
            description = "Test your knowledge to earn your Navigate between screens badge.",
            type = ActivityType.Quiz,
            url = "https://developer.android.com/courses/quizzes/android-basics-kotlin-unit-3-pathway-1/android-basics-kotlin-unit-3-pathway-1",
            progress = 25
        ),
        ActivityEntity(
            pathwayId = 9,
            number = 5,
            title = "Quiz",
            description = "Test your knowledge and earn your Introduction to the Navigation Component badge.",
            type = ActivityType.Quiz,
            url = "https://developer.android.com/courses/quizzes/android-basics-kotlin-unit-3-pathway-2/android-basics-kotlin-unit-3-pathway-2",
            progress = 25
        ),
        ActivityEntity(
            pathwayId = 10,
            number = 4,
            title = "Quiz",
            description = "Test your knowledge and earn your Architecture components badge.",
            type = ActivityType.Quiz,
            url = "https://developer.android.com/courses/quizzes/android-basics-kotlin-unit-3-pathway-3/android-basics-kotlin-unit-3-pathway-3",
            progress = 30
        ),
        ActivityEntity(
            pathwayId = 11,
            number = 5,
            title = "Quiz",
            description = "Test your knowledge and earn your Advanced navigation app examples badge.",
            type = ActivityType.Quiz,
            url = "https://developer.android.com/courses/quizzes/android-basics-kotlin-unit-3-pathway-4/android-basics-kotlin-unit-3-pathway-4",
            progress = 25
        ),
        ActivityEntity(
            pathwayId = 12,
            number = 5,
            title = "Quiz",
            description = "Test your knowledge and earn your Adaptive layouts badge.",
            type = ActivityType.Quiz,
            url = "https://developer.android.com/courses/quizzes/android-basics-kotlin-unit-3-pathway-5/android-basics-kotlin-unit-3-pathway-5",
            progress = 30
        ),
        ActivityEntity(
            pathwayId = 13,
            number = 3,
            title = "Quiz",
            description = "Test your knowledge and earn your Coroutines badge.",
            type = ActivityType.Quiz,
            url = "https://developer.android.com/courses/quizzes/android-basics-kotlin-unit-4-pathway-1/android-basics-kotlin-unit-4-pathway-1",
            progress = 50
        ),
        ActivityEntity(
            pathwayId = 14,
            number = 6,
            title = "Quiz",
            description = "Test your knowledge of HTTP/REST, and earn your Get and display data from " +
                    "the internet badge.",
            type = ActivityType.Quiz,
            url = "https://developer.android.com/courses/quizzes/android-basics-kotlin-unit-4-pathway-2/android-basics-kotlin-unit-4-pathway-2",
            progress = 25
        ),
        ActivityEntity(
            pathwayId = 15,
            number = 3,
            title = "Quiz",
            description = "Test your knowledge of relational databases and the Room library, " +
                    "and earn your SQL, Room, and Flow badge.",
            type = ActivityType.Quiz,
            url = "https://developer.android.com/courses/quizzes/android-basics-kotlin-unit-5-pathway-1/android-basics-kotlin-unit-5-pathway-1",
            progress = 30
        ),
        ActivityEntity(
            pathwayId = 16,
            number = 7,
            title = "Quiz",
            description = "Test your knowledge of the Room library, and earn your data persistence badge.",
            type = ActivityType.Quiz,
            url = "https://developer.android.com/courses/quizzes/android-basics-kotlin-unit-5-pathway-2/android-basics-kotlin-unit-5-pathway-2",
            progress = 25
        ),
        ActivityEntity(
            pathwayId = 17,
            number = 5,
            title = "Quiz",
            description = "Test your knowledge of WorkManager, and earn your WorkManager badge.",
            type = ActivityType.Quiz,
            url = "https://developer.android.com/courses/quizzes/android-basics-kotlin-unit-6-pathway-1/android-basics-kotlin-unit-6-pathway-1",
            progress = 30
        )
    )
}