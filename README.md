# JavaFX + Maven = Native Desktop Apps

[Spring Boot](https://spring.io/projects/spring-boot) +
[jpackage](https://docs.oracle.com/en/java/javase/15/docs/specs/man/jpackage.html) +
[Maven](http://maven.apache.org) template project for generating native desktop applications.

# Goal

1. Build nice, small cross-platform [Spring Boot](https://spring.io/projects/spring-boot) based desktop apps with native
   installers
    - Apx 30-40mb .dmg, .msi and .deb installers - check out the example builds in
      [releases](https://github.com/wiverson/desktop-spring-boot/releases).
2. Just use Maven - no shell scripts required.
    - Use standard Maven dependency system to manage dependencies
3.

Generate [macOS (.dmg), Windows (.msi) and Unix (e.g. deb/rpm)](https://github.com/wiverson/maven-jpackage-template/releases)
installers/packages automatically
with [GitHub Actions](https://github.com/wiverson/maven-jpackage-template/tree/main/.github/workflows)

In many ways this project provides a Java developer with tooling similar to Electron
or [Neutralino.js](https://neutralino.js.org)

## Overview

This template uses a [Maven plugin](https://github.com/wiverson/jtoolprovider-plugin) to generate a custom JVM and
installer package for a Spring Boot application run as a desktop app. Conceptually this is similar to Electron

The basic requirements are just Java 16 and Maven. [Java 15 will work](docs/java-15-jpackage.md), although it requires a
bit of setup.

- On macOS XCode is required.
- On Windows the free [WiX Toolset](https://wixtoolset.org/) is required.

The project includes [GitHub Actions](https://github.com/wiverson/maven-jpackage-template/tree/main/.github/workflows)
which automatically generate macOS, Windows, and Linux installers.

The generated installers come in at around 30-40mb. The example source in the project includes demonstrations of several
native desktop features - for example, drag-and-drop from the Finder/Explorer, as well as a few macOS Dock integration
examples. Removing the code and the demonstration dependendencies gets a "Hello World" build size closer to 30mb than
40mb.

## Key Features

Here are few cool things in this template:

- Only uses Java and Maven. No shell scripts required.
- Includes sample [GitHub Actions](https://github.com/wiverson/maven-jpackage-template/tree/main/.github/workflows) to
  build macOS, Windows and Linux installers
- Demonstrates setting the application icon
- Builds a .dmg on macOS, .msi on Windows, and .deb on Linux
- Bundles the JavaFX SDK & modules to simplify getting started.
- Template includes several examples of JavaFX / native desktop integration
    - Drag & drop with Finder / Explorer
    - Change the Dock icon dynamically on macOS
    - Menu on the top for macOS, in the window itself on Windows
    - Request user attention (bouncing dock icon) on macOS

Once you get started, you might find these lists of tutorials, tools, libraries for
[JavaFX](https://gist.github.com/wiverson/6c7f49819016cece906f0e8cea195ea2)
and general [Java desktop integration](https://gist.github.com/wiverson/e9dfd73ca9a9a222b2d0a3d68ae3f129) helpful.

# Usage

Once everything is installed (see below) it's really easy to use:

To generate an installer, just run...

`mvn clean install`

To do everything up until the actual installer generation (including generating the custom JVM)...

`mvn clean package`

# Installation

1. Install [OpenJDK Java 16](https://adoptopenjdk.net/) or
   [Oracle Java 16](https://www.oracle.com/java/technologies/javase-downloads.html).
    - Verify by opening a fresh Terminal/Command Prompt and typing `java --version`.
2. Install [Apache Maven 3.6.3](http://maven.apache.org/install.html) or later and make sure it's on your path.
    - Verify this by opening a fresh Terminal/Command Prompt and typing `mvn --version`.
3. macOS: verify XCode is installed and needed agreements accepted.
    - Launch XCode and accept the license, or verify in Terminal with the command `sudo xcodebuild -license`.
5. Windows: install [Wix 3 binaries](https://github.com/wixtoolset/wix3/releases/).
    - Installing Wix via the installer should be sufficient for jpackage to find it.
3. Clone/download this project.
6. Final step: run `mvn clean install` from the root of the project to generate the `target\TestApp.dmg`
   or `target\TestApp.msi` (installer).
    - Note that the actual generated installer will include a version number in the file name
    - For reference, here is a complete run log for [a successful run](docs/sample-run.md).

Because these builds use stripped down JVM images, the
[generated installers are in the 30-40mb range](https://github.com/wiverson/maven-jpackage-template/releases).

# Sponsor

This project is sponsored by [ChangeNode.com](https://changenode.com/) - if you would like to add easy automatic
updates, crash reporting, analytics, etc. to your Java/JavaFX desktop application, go check it out... and be sure to
subscribe for more information about desktop Java development.

# Help

Problems? Make sure everything is installed and working right!

- Compiler not recognizing the --release option? Probably on an old JDK.
- Can't find jdeps? Probably on an old JDK.
- Can't find jpackage? Probably haven't set up your system
  to [allow Java 15 to enable preview packages]((docs/java-15-jpackage.md)).
- Unrecognized option: --add-modules jdk.incubator.jpackage
    - Could be a left-over MAVEN_OPTS setting when you switched from Java 15 to Java 16
    - If you are still on Java 15, you may not have
      [MAVEN_OPTS set correctly](https://github.com/wiverson/maven-jpackage-template/issues/2).

If you need consulting support, feel free to reach out to [ChangeNode.com](https://changenode.com/).

# Q&A

If you are using the template, browsing the [Q&A](docs/qna.md) is highly recommended.
