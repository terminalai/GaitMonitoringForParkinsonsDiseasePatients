function showAndroidToast(toast) {
    Android.showToast(toast);
}

// Getting value from Android
function showVersion(msg) {
    var myVar = Android.getAndroidVersion();
    // document.getElementById("version").innerHTML = msg + " You are running API Version " + myVar;
}