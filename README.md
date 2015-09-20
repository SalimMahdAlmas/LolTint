# LolTint


**Current version : 1.2**

**About LolTint :**
The world first library to tint statusbar without hanging with styles.xml in android 5.0+

**How to implement :**

1. Download LolTint.jar
2. Implement the LolTint.jar or import the lib project
3. In your class add this
 
    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);

        LolTint.on(true,false,true,Color.COLOR_DEEP_ORANGE,this);

    }
4.Run your project done

*Changelog :**
- *v1.0 :*

 >>initial release.

- *v1.1 :*
 >> fix issue
- *v1.2 :*
 >> no need to extends now
 >> added new features
 *Credits :**
 Sahid Almas
