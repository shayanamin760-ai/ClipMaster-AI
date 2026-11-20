# ClipMaster AI Android project

This is the Android app shell (APK build project), not a finished installable APK.

## Important
The app loads the ClipMaster web processor inside an Android WebView. A deployed HTTPS backend is required for video uploads, transcription, clip generation and downloads. The APK cannot process video by itself.

## Configure backend
1. Deploy the Streamlit ClipMaster project from `ClipMaster_AI_Starter.zip` to a server that supports FFmpeg.
2. Copy its HTTPS address.
3. Replace `https://YOUR-CLIPMASTER-SERVER.example` in `app/build.gradle.kts` under `APP_URL`.
4. Build the APK.

## Build without a local computer
1. Create a GitHub repository and upload all files in this project.
2. Open the repository's **Actions** tab.
3. Select **Build Android APK** and tap **Run workflow**.
4. When the workflow completes, open its run and download the `ClipMasterAI-debug-apk` artifact.
5. Extract the APK and install it on your Android phone. Android may ask you to allow installation from that source.

## Build with Android Studio
Open this folder in Android Studio and run `gradle assembleDebug`. APK output:
`app/build/outputs/apk/debug/app-debug.apk`

## Features / current status
- Android launcher app named ClipMaster AI
- Embedded mobile web interface
- Video file chooser support for the hosted web app
- Web backend must be deployed and configured
- YouTube URL ingestion is not implemented; use creator-provided original video files or authorized exports.
