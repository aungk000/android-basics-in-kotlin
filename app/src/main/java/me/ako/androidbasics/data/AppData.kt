package me.ako.androidbasics.data

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
            "Kotlin basics",
            "Take your first steps programming in Kotlin, add images and text to " +
                    "your Android apps, and learn how to use classes, objects, and conditionals " +
                    "to create an interactive app for your users."
        ),
        UnitEntity(
            2,
            "Layouts",
            "Build two different apps, and improve the user interface of your app by " +
                    "learning about layouts, Material Design guidelines, and best practices for " +
                    "UI development"
        ),
        UnitEntity(
            3,
            "Navigation",
            "Enhance your user's ability to navigate across, into and back out from " +
                    "the various screens within your app for a consistent and predictable user " +
                    "experience."
        ),
        UnitEntity(
            4,
            "Connect to the internet",
            "Write coroutines for complex code, and learn about HTTP and REST to get " +
                    "data from the internet. Then, use the Coil library to display images in " +
                    "your app."
        ),
        UnitEntity(
            5,
            "Data persistence",
            "Keep your apps working through any disruptions to essential networks or " +
                    "processes for a smooth and consistent user experience."
        ),
        UnitEntity(
            6,
            "WorkManager",
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
        )
    )
}