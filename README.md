# Android-Toggle-Switch
A customizable extension to Android switches that supports many items.

![Sample of libray](docs/screen.jpg)


## Installation

#### Gradle
Add Gradle dependency:

```
dependencies {
	compile 'us.belka:androidtoggleswitch:1.0'
}
```

#### Maven
```
<dependency>
  <groupId>us.belka</groupId>
  <artifactId>androidtoggleswitch</artifactId>
  <version>1.0</version>
  <type>pom</type>
</dependency>
```

## Usage

#### 2 Items 

```xml
<belka.us.androidtoggleswitch.widgets.ToggleSwitch
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_gravity="center"
        custom:textToggleLeft="OR"
        custom:textToggleRight="AND"/>
```

![Sample of libray](docs/2_items.jpg)

#### 3 Items 

```xml
<belka.us.androidtoggleswitch.widgets.ToggleSwitch
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_gravity="center"
        custom:textToggleCenter="XOR"
        custom:textToggleLeft="OR"
        custom:textToggleRight="AND"/>
```


## Listener

```java
ToggleSwitch toggleSwitch = (ToggleSwitch) findViewById(R.id.toggle_switch);
toggleSwitch.setOnToggleSwitchChangeListener(new ToggleSwitch.OnToggleSwitchChangeListener(){

            @Override
            public void onToggleSwitchChangeListener(int position) {
				Write your code ... 
            }
        });
```

## Customizations

## Contributors
Lorenzo Rigato, Android Developer @[Belka](https://github.com/BelkaLab)

## License
Android-Toggle-Switch is Copyright (c) 2016 Belka, srl. It is free software, and may be redistributed under the terms specified in the LICENSE file.  

## About Belka
![Alt text](http://s2.postimg.org/rcjk3hf5x/logo_rosso.jpg)

[Belka](http://belka.us/en) is a Digital Agency specialized in design, mobile applications development and custom solutions.
We love open source software! You can [see our projects](http://belka.us/en/portfolio/) or look at our case studies.

Interested? [Hire us](http://belka.us/en/contacts/) to help build your next amazing project.

[www.belka.us](http://belka.us/en)
