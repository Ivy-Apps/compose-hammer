# Compose Hammer

![Build](https://github.com/ILIYANGERMANOV/compose-material3-helper-plugin/workflows/Build/badge.svg)
[![Version](https://img.shields.io/jetbrains/plugin/v/PLUGIN_ID.svg)](https://plugins.jetbrains.com/plugin/PLUGIN_ID)
[![Downloads](https://img.shields.io/jetbrains/plugin/d/PLUGIN_ID.svg)](https://plugins.jetbrains.com/plugin/PLUGIN_ID)

<!-- Plugin description -->
Compose Hammer is **a premium Android Studio Jetpack Compose Material3 UI builder plugin**
that supports **59+ Material3 components** and
**30+ Jetpack Compose code patterns** like
`remember`, `mutableStateOf`, `LaunchedEffect`s, animations, layouts and more!

**TL;DR;**

- **Preview directly in Android Studio** without building the Material3 (MaterialYou) components
  from https://m3.material.io.
- **A copy-paste ready implementation code** including imports for
  the Material3 Jetpack Compose components.
- **A quick action (⌥ Option/Alt + ⤶ Enter)** that auto-magically
  generates and inserts the code for you with all the imports necessary.
- **Full customization code** for the M3 components, so you can more
  easily make them match your design requirements.
- **Custom templates**: create custom code snippets and components.
- **Relevant links** to the [M3 Spec](https://m3.material.io/components/buttons/specs) _(anatomy)_,
  [M3 Guidelines](https://m3.material.io/components/buttons/guidelines) _(usage)_, and
  the [Official Jetpack Compose docs](https://developer.android.com/reference/kotlin/androidx/compose/material3/package-summary.html)
  _(code)_
  for all components, so you can quickly find the info that you need.

If you haven't done already, watch the
[Compose Hammer demo video](https://www.youtube.com/watch?v=07Yeogvw0wo).
Better show, than tell!

In a nutshell, Compose Hammer is so slick that even Thor’s Mjölnir will get envy.
Our goal is to make Android UI development with **Jetpack Compose - easy, fun and fast.
⚡🔨**

### ⚡ QuickCode _(custom code snippets)_

Create custom components and code templates that will be
available in our new **⚡ QuickCode alt/option + enter shortcut**.

### Tool window features _(Android Studio's right sidebar)_

- _Note:_ These are all **also supported** in the alt/option + enter quick action.
- **Buttons**
  - [Elevated button](https://m3.material.io/components/buttons/specs#2a19e853-d5dc-46a2-8ef4-1d954c9dcefa)
  - [Filled button](https://m3.material.io/components/buttons/specs#0b1b7bd2-3de8-431a-afa1-d692e2e18b0d)
  - [Filled tonal button](https://m3.material.io/components/buttons/specs#158f0a18-67fb-4ac4-9d22-cc4d1adc4579)
  - [Outlined button](https://m3.material.io/components/buttons/specs#de72d8b1-ba16-4cd7-989e-e2ad3293cf63)
  - [Text button](https://m3.material.io/components/buttons/specs#899b9107-0127-4a01-8f4c-87f19323a1b4)
- **Floating action buttons**
  - [Floating action button](https://m3.material.io/components/floating-action-button/specs#71504201-7bd1-423d-8bb7-07e0291743e5)
  - [Small floating action button](https://m3.material.io/components/floating-action-button/specs#df918e03-5939-4aa4-8d4b-4cdffa52b240)
  - [Large floating action button](https://m3.material.io/components/floating-action-button/specs#9d7d3d6a-bab7-47cb-be32-5596fbd660fe)
  - [Extended floating action button](https://m3.material.io/components/extended-fab/specs#8c06766e-0afc-436f-a695-aa589700be14)
- **Icon buttons**
  - [Standard icon button](https://m3.material.io/components/icon-buttons/specs#eca0451e-430b-41e1-bea3-a31cb7ccda76)
  - [Filled icon button](https://m3.material.io/components/icon-buttons/specs#d4169fb5-4cf8-40b6-9ec3-4044f09cca1f)
  - [Filled tonal icon button](https://m3.material.io/components/icon-buttons/specs#c2ca424b-2ad7-40e6-8946-47fb1918060a)
  - [Outlined icon button](https://m3.material.io/components/icon-buttons/specs#632e1356-8002-4ae1-ae36-48c1f9b17ef2)
- **Text fields**
  - [Filled text field](https://m3.material.io/components/text-fields/specs#6d654d1d-262e-4697-858c-9a75e8e7c81d)
  - [Outlined text field](https://m3.material.io/components/text-fields/specs#68b00bd6-ab40-4b4f-93d9-ed1fbbc5d06e)
- **Cards**
  - [Elevated card](https://m3.material.io/components/cards/specs#a012d40d-7a5c-4b07-8740-491dec79d58b)
  - [Filled card](https://m3.material.io/components/cards/specs#6192bdaa-bd56-45c9-97ff-d540ce5337ac)
  - [Outlined card](https://m3.material.io/components/cards/specs#9ad208b3-3d37-475c-a0eb-68cf845718f8)
- **Checkboxes**
  - [Checkbox](https://m3.material.io/components/checkbox/specs)
  - [Tri-state checkbox](https://m3.material.io/components/checkbox/specs)
- **Switches**
  - [Switch](https://m3.material.io/components/switch/specs)
- **Radio buttons**
  - [Radio button](https://m3.material.io/components/radio-button/specs)
- **Sliders**
  - [Slider](https://m3.material.io/components/sliders/specs)
  - [Range slider](https://m3.material.io/components/sliders/specs)
- **Bottom sheets**
  - [Bottom sheet](https://m3.material.io/components/bottom-sheets/specs)
- **Dialogs**
  - [Alert dialog](https://m3.material.io/components/dialogs/specs)
- **Badges**
  - [Badge](https://m3.material.io/components/badges/specs)
- **Lists**
  - [List item](https://m3.material.io/components/lists/specs)
- **Dividers**
  - [Divider](https://m3.material.io/components/divider/specs)
- **Chips**
  - [Assist chip](https://m3.material.io/components/chips/specs#a144389c-9478-4fe4-9bd8-ca9f7dd830eb)
  - [Filter chip](https://m3.material.io/components/chips/specs#e900592f-75a4-4298-853c-bedd8f462f83)
  - [Input chip](https://m3.material.io/components/chips/specs#facb7c02-74c4-4b81-bd52-6ad10ce351eb)
  - [Suggestion chip](https://m3.material.io/components/chips/specs#67a358c0-c370-4bf1-b410-7f8dd3f1a60c)
- **Dropdown menus**
  - [Dropdown menu](https://m3.material.io/components/menus/specs)
  - [Exposed dropdown menu](https://m3.material.io/components/menus/specs)
- **Top app bars**
  - [Center-aligned top app bar](https://m3.material.io/components/top-app-bar/specs#51ac0fae-61c2-4abc-b8f9-1167bf54e875)
  - [Small top app bar](https://m3.material.io/components/top-app-bar/specs#14e23895-ac2e-40d8-b0f7-8d016c10a225)
  - [Medium top app bar](https://m3.material.io/components/top-app-bar/specs#e3fd3eba-0444-437c-9a82-071ef03d85b1)
  - [Large top app bar](https://m3.material.io/components/top-app-bar/specs#8140aaaf-5729-4368-a0f5-baef8d576dbf)
- **Tabs**
  - [Tabs](https://m3.material.io/components/tabs/specs)
  - [Tabs with pager](https://m3.material.io/components/tabs/specs)
  - [Horizontal pager]()
- **Navigation bars**
  - [Navigation bar](https://m3.material.io/components/navigation-bar/specs)
- **Navigation drawers**
  - [Modal navigation drawer](https://m3.material.io/components/navigation-drawer/specs)
  - [Dismissible Navigation drawer](https://m3.material.io/components/navigation-drawer/specs)
- **Navigation rails**
  - [Navigation rail](https://m3.material.io/components/navigation-rail/specs)
- **Bottom app bars**
  - [Bottom app bar](https://m3.material.io/components/bottom-app-bar/specs)
- **Progress indicators**
  - [Linear determinate](https://m3.material.io/components/progress-indicators/specs#b4bf0322-bfe6-4fad-babc-7802c691f135)
  - [Linear indeterminate (infinite)](https://m3.material.io/components/progress-indicators/specs#b4bf0322-bfe6-4fad-babc-7802c691f135)
  - [Circular determinate](https://m3.material.io/components/progress-indicators/specs#c6a801ca-8a87-4529-8eb1-2c8e9791e3b0)
  - [Circular indeterminate (infinite)](https://m3.material.io/components/progress-indicators/specs#c6a801ca-8a87-4529-8eb1-2c8e9791e3b0)
- **Snackbars**
  - [Snackbar](https://m3.material.io/components/snackbar/specs)
- **Date Pickers**
  - [Date picker (input)](https://m3.material.io/components/date-pickers/specs#ccd8cb55-4c20-4832-9db2-7c14c49b6e8f)
  - [Date picker (calendar)](https://m3.material.io/components/date-pickers/specs#d58626b9-ed69-4963-a75c-18d00cae5a06)
  - [Date picker dialog](https://m3.material.io/components/date-pickers/specs#d58626b9-ed69-4963-a75c-18d00cae5a06)
  - [Date range picker](https://m3.material.io/components/date-pickers/specs#d3189372-1b73-49d2-977e-e766f43a2774)
- **Time pickers**
  - [Time picker dial](https://m3.material.io/components/time-pickers/specs#656721f2-de86-4311-807d-f295bddfb72f)
  - [Time picker input](https://m3.material.io/components/time-pickers/specs#f07ad824-7e63-4d86-b5ca-090f1a6a3ded)
- **Search bars**
  - [Search bar](https://m3.material.io/components/search/specs)
  - [Docked search bar](https://m3.material.io/components/search/specs)
- **Tooltips**
  - [Plain tooltip](https://m3.material.io/components/tooltips/specs#92c84fef-92fe-4662-b837-f70eaa9b64f3)

### Quick action support _(⌥ Option/Alt + ⤶ Enter)_

- ⚡ Quick UI
  - Text
  - Icon
  - Image
  - Spacer (vertical)
  - Spacer (horizontal)
  - Spacer (weight)
  - modifier = Modifier
  - modifier: Modifier = Modifier
  - RoundedCornerShape (dp)
  - RoundedCornerShape (fully rounded)
- ⚡ Layouts
  - Column
  - Row
  - Box
  - Lazy Column
  - Lazy Row
- ⚡ Compose runtime
  - by remember { mutableStateOf(false) }
  - by remember { mutableStateOf<String?>(null) }
  - derivedStateOf {}
  - produceState {}
  - Coroutine scope
  - LaunchedEffect
  - with(LocalDensity.current) {}
  - Open link in the browser
  - LocalContext.current
- ⚡ Animations
  - Visibility: expand/shrink
  - Visibility: slide in/out
  - Crossfade
  - Animate Float
  - Animate Dp
  - Animate Color

_Pst: Keep in mind - this an MVP ⚠️, and it’ll only get better. That being said,
your feedback and encouragement _(positive reviews)_ are highly appreciated!
This will motivate us to work harder and make the plugin better._

**P.S.** Feel free to report any bugs,
missing components or your feedback at our [Compose Hammer Telegram chat](https://t.me/+U9Qn68cZxYIxNDA0).
<!-- Plugin description end -->