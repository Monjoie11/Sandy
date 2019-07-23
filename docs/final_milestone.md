## Final Milestone Notes and Instructions

Are you tired of Android applications that "perform as you expect them too?" Do you think that functioning unit tests are just another way for the patriarchy to spread dissension amongst the proletariat? Do yu think that of all the flavors at a lunch counter, failure on rye is THE American classic? Then boy howdy, do I have an app for you!!!

To properly view Sandy, your phone or emulator must be running the Android API 28 or higher, or the camera fragment will call you names as it mysteriously closes itself without any warning or error.

If you're using one of the standard Android Studio emulators, I suggest the 5X, as the Nexus 6 doesnt appear in Stetho.

After you successfully sign-in via Google, please take your time with the tutorial. Allow each snackbar to disappear before making the next selection, as rushing not only robs you of fully appreciating Sandy's wit, but it can force the app to skip over images (specifically image 8, for reasons beyond my powers of comprehension).

After finishing your tutorial, Sandy will send a packet of images to Clarifai to load them around a concept to be used in creating and training a model tailored to your tastes. Clarifai will receive these files and then promptly delete them. This is actually good news. It appears the code is mostly correct; it's the rather complicated backend configuration of the service that I didn't have time to fully learn and set up for custom model creation.

While that magic, or lack thereof, is taking place, you will see the camera fragment load. When you take a picture, nothing happens. As I can't properly train a model yet, there's nothing to compare the image to and thus no place (version number) to send it, so the code's not implemented. Taking a picture will 1) send that image to Clarifai to be compared to your model, 2) load the image and Clarifai's determination onto the response screen. If you then say or confirm that the image is a sandwich, it will 3) be sent back to Clarifai, this time as a model input, to improve Sandy's education.

Finally, unit tests. I never got them to compile. Never. IntelliJ believes sandwichId is a null value despite setting a value to it. I'm ot of time, out of Red Bull, and am frankly a little frayed.

Sandy Alpha 1.0.0 is not what I'd hoped it would be, but there's some good code in there, and a lot of reason to think that Sandy Beta will be a tasty morsel indeed.