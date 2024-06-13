# 04DirectoryMeta

This project was generated with [Angular CLI](https://github.com/angular/angular-cli) version 17.3.7.

## Development server

Run `ng serve` for a dev server. Navigate to `http://localhost:4200/`. The application will automatically reload if you change any of the source files.

## Code scaffolding

Run `ng generate component component-name` to generate a new component. You can also use `ng generate directive|pipe|service|class|guard|interface|enum|module`.

## Build

Run `ng build` to build the project. The build artifacts will be stored in the `dist/` directory.

## Running unit tests

Run `ng test` to execute the unit tests via [Karma](https://karma-runner.github.io).

## Running end-to-end tests

Run `ng e2e` to execute the end-to-end tests via a platform of your choice. To use this command, you need to first add a package that implements end-to-end testing capabilities.

## Further help

To get more help on the Angular CLI use `ng help` or go check out the [Angular CLI Overview and Command Reference](https://angular.io/cli) page.

## MetaData

Use `preserveWhitespaces: true` for adding white spaces between elements

## viewProviders

provide class name in `viewProviders: [demo]` as to get rid out of no provider error while other class is used in standalone component

## Encapsulation

For passing the styles from child to parent and parent to child.

-->If provide this `encapsulation:ViewEncapsulation.None` to component then style will apply to it's parent and child both. except their individual css.
-->If provide this `encapsulation:ViewEncapsulation.Emulated` to component then style will apply to that comp only.
-->If provide this `encapsulation:ViewEncapsulation.ShadowDom` to component then style will apply to Its child component only.

## Interpolation VS PropertyBinding

-->Interpolation fails when use of boolean value it not proper

## Event-Binding

-->Two ways to write - 1. `<input (click)="onChange()" type="text"/>` 2.`<input on-click="onChange()" type="text"/>`

## Two way Data Binding

--> Use `[(ngModule)]=<variablename>`

## Pipes

Built-in Pipes in Angular
Angular comes with a set of built-in pipes that you can use in your templates. Here are some of the most commonly used built-in pipes in Angular:

Currency Pipe
Date Pipe
Json Pipe
LowerCase Pipe
UpperCase Pipe
PercentPipe
SlicePipe
TitleCasePipe
AsyncPipe

## Services 

--> We use the service by object creation in the constructor so if service purpose get accomplished then it automatically deconstruct memory so its best practice to init in the constructor

--> Tree shaking in the service means if services are made but not any single object present for that then it means it is not in use so it automatically remove that code in build process. it enhance the performance  

## Reactive Form

--> Set and patch value. use patch for only some data update and set for whole data update including id.

## Get Value & Status Change
