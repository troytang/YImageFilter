# YImageFilter #
A android image filter library by JNI.

# Usage #

### Gradle ###
1.Add this dependency to your build.gradle file:
```
dependencies {
    compile 'com.tangwy:yimagefilter:0.2.0'
}
```

2.Add the `.so` files to your app project file's tree:
`Android Studio` for example. You should copy the `jniLibs` file to your app project, the file path must `src\main\jniLibs`


### Basic Usage ###

* uses without animation
```
ivOld = (ImageView) findViewById(R.id.ivOld);
ivOld.setImageBitmap(YImageFilter.filter(new OldFilter(), BitmapFactory.decodeResource(getResources(), R.mipmap.blur)));
```

* uses with animation
```
ivChange = (ImageView) findViewById(R.id.ivChange);
od = new FilterDrawable(ivChange, new BlackWhiteFilter(), BitmapFactory.decodeResource(MainActivity.this.getResources(), R.mipmap.blur), false);
ivChange.setImageDrawable(od);
ivChange.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        od.start();
    }
});
```

# License #

```
Copyright 2015 Troy Tang

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

   http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```