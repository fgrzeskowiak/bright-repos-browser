# Bright Repos Browser

A simple Android app designed to browse commits of a Github repository.
The App consists of 2 screens:

- Search Screen - provides input for repository search in "owner/repo" format and Recent Searches
  list
- Repository Details screen - displays the searched repository name, id and commit history. It also
  allows selecting commits by clicking on them. The selected commits can be then shared with some
  3rd party apps in a formatted message.

The App supports Light and Dark themes, based on system settings.

<p>
<img width="180" alt="" src="https://github.com/fgrzeskowiak/bright-repos-browser/assets/17904175/0b5052c8-a535-4463-986a-2462611ee963">
<img width="180" alt="" src="https://github.com/fgrzeskowiak/bright-repos-browser/assets/17904175/c96dd923-c409-4861-bdd5-317c760e802f">
<img width="180" alt="" src="https://github.com/fgrzeskowiak/bright-repos-browser/assets/17904175/22e7a5ba-e407-47a0-95c2-3f81de0a771d">
<img width="180" alt="" src="https://github.com/fgrzeskowiak/bright-repos-browser/assets/17904175/df659ab6-7653-4103-9bed-b97bb13199aa">
</p>

## Required setup

The project requires a `secret.properties` file placed inside root directory. It should contain the
base API url in the following format:

```
baseUrl=url
```

Compiling the project might also require a Java 17 as a Gradle JDK.

## Design decisions

In order to keep the readability and separation of concern inside the codebase, it was split into
separate modules, grouped into 2 sections: `core` and `feature`.

Modules inside `core` provide functionalities used by `feature` modules: `network`, `database`,
`navigation` and `common` classes.

Modules inside `feature` represent the 2 aforementioned screens: `search` and `details`.

In order to simplify modules setup, a dedicated `buildSrc` folder was created, containing all the
build logic, implemented as
3 [Gradle Convention Plugins](https://docs.gradle.org/current/samples/sample_convention_plugins.html)
.

The Project uses Jetpack Compose as a UI framework and a simplified version of MVI architecture -
the screens read only 1 state stream, but the execution logic uses dedicated methods rather than
Intent/Actions interfaces with reducers.

In the business logic classes, the [Arrow](https://arrow-kt.io/) library was used to model error and
success scenarios.

The codebase contains unit tests for the most important classes, which use such
as [kotest](https://kotest.io/) and [turbine](https://github.com/cashapp/turbine).

## Future enhancements

All the minimal project requirements were met, but the time limit did not allow to implement some
additional logic.

Some future enhancements might include:

- Adding support for paging logic. Current implementation fetches only the first page of repository
  commits in order to limit network and database usage. The idea was to use Paging3 library, but the
  implementation (including database handling and Compose support) was too time consuming.
- Adding pull to refresh mechanism to the Repository Details screen. Current implementation always
  gets the data from the database if it's not empty. There should be an option for the user to
  refresh the data.
- Some more test scenarios should be created, especially for ViewModels. Current implementation
  tests only the happy path and navigation.
- UI look should be enhanced. Current implementation serves the required functionality, but is not
  too aesthetic.
- Previews should be added to screen files. Current implementation doesn't include them as they did
  not work automatically after adding the required dependency and the time limit did not allow for
  further investigation of the root cause.
- Navigation builder should probably be implemented better, so that the screen Composables, their
  routes and arguments are not public, but internal. The feature module should only expose a method
  to build its navigation graph
