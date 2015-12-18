# Fritter Factory
Library to automatically populate models, to be used in automatic tests or GUI tests.
Designed to be used in Android (only uses some parts of Java reflection).

In alpha state.

To add it to your project:

Step 1. Add this to your root build.gradle at the end of repositories:

	allprojects {
		repositories {
			...
			maven { url "https://jitpack.io" }
		}
	}
	
Step 2. Add the dependency (replace eead5abe04 with latest commit ssha)

	dependencies {
	        compile 'com.github.mateuyabar.fritterfactory:fritterfactory:eead5abe04'
	}
	
