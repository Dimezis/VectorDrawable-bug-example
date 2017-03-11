# VectorDrawable-bug-example
An example of Android's VectorDrawable bug

OS version 7.1

VectorDrawable starts to display its blurred (downscaled) version, if you are trying to draw it on both DisplayListCanvas and manually created software Canvas with scale factor.
I was able to decouple a code sample which triggers this bug, though I don't understand its real nature and what's happening internally in VectorDrawable.

Steps to reproduce:

1) Create a list of ImageViews. Set src attribute to use some vector image.
For example, I have used a regular RecyclerView populated with this kind of ImageViews.

2) Create a custom View with overridden draw() method (full View code is here https://github.com/Dimezis/VectorDrawable-bug-example/blob/master/app/src/main/java/com/eightbitlab/vectordrawablebugexample/LolView.java):

    public void draw(Canvas canvas) {
        super.draw(canvas);
        if (canvas != softwareCanvas) {
            softwareCanvas.save();
            //scale is a main trigger here, VectorDrawable will be  downscaled just as much as this canvas
            softwareCanvas.scale(1f / 8, 1f / 8);
            //draw all views on our canvas
            root.draw(softwareCanvas);
            softwareCanvas.restore();
            invalidate();
        }
    }

3) Add this View to Activity with the list of ImageViews and pass a root of Activity to this View.

Result:

Despite the fact that I'm not drawing softwareCanvas' content anywhere, the images in list will become blurred, i.e. the state of VectorDrawable is getting mutated somehow by this softwareCanvas' scale factor.

What should happen:

VectorDrawable should be displayed correctly.

Note that VectorDrawableCompat, which is used on versions prior to SDK 24, works fine in this scenario. 
A regular PNG image (a BitmapDrawable) also has no issues.

Though the described flow looks kinda weird and complicated, it's a part of real use case in my BlurView library (https://github.com/Dimezis/BlurView), which relies on drawing the View hierarchy on own Canvas for later use.
