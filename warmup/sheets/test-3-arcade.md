#### The input

You will read a JSON document containing an array of *game* objects.

##### The object describing a *game*

Each object has the following properties:

1. `name`: the name of the *game* (JSON type: `string`)
1. `description`: the description of the *game* (JSON type: `string`)
1. `platform`: the platform of the *game* (JSON type: `string`)

   This property contains one of the following values:

   1. `"Neo Geo"`
   1. `"Capcom Play System"`
   1. `"Sega Model Series"`
   1. `"Nintendo"`

1. `released`: the release year of the *game* (JSON type: `number`, contains integers)
1. `characters`: the characters of the *game* (JSON type: `array`, contains objects, each element represents a *character*)

##### The object describing a *character*

Each object has the following properties:

1. `name`: the name of the *character* (JSON type: `string`)
1. `description`: the description of the *character* (JSON type: `string`)
1. `games`: the number of games of the *character* (JSON type: `number`, contains integers)

#### Creating the representation

Define the model classes in the package `arcade` to represent the given JSON document using *Lombok*. The names of the classes and the fields are mandatory.

1. Create the class `Game` representing a *game*. Follow the given instructions:

   1. Make the class immutable (guarantee that the fields are invisible from outside of the class and cannot be modified, and the class cannot be extended).
   1. Field `platform` must be represented as an enum constant (see the details later).
   1. Field `characters` must be represented as a list.
   1. Generate a constructor having all the parameters. Use the public visibility.
   1. Generate a constructor having no parameter. Use the private visibility.
   1. Generate a getter method for each field. Use the public visibility.
   1. Generate a `toString()` method which contains all the fields except the `characters` field.
   1. Two objects are identical if their `name` fields are the same.

1. Create the class `Game.Character` representing a *character* as the public static nested class of the class `Game`. Follow the given instructions:

   1. Make the class immutable (guarantee that the fields are invisible from outside of the class and cannot be modified, and the class cannot be extended).
   1. Generate a constructor having all the parameters. Use the public visibility.
   1. Generate a constructor having no parameter. Use the private visibility.
   1. Generate a getter method for each field. Use the public visibility.
   1. Generate a `toString()` method which contains all the fields.
   1. Two objects are identical if their `name` fields are the same.

1. Create the enum class `Game.Platform` representing the possible values of the field `platform` as the public static nested enum class of the class `Game`.

#### Extending the `Repository` class

You got the abstract `base.Repository<T>` class that is capable of reading a JSON document using the *Jackson* library, and store the objects in the memory.

1. Define the class `GameRepository` in the package `arcade`.
1. Extend the `Repository` class as the following:

   1. provide the class `Game` as its generic type parameter
   1. define a parameterless constructor to support the instantiation of your class

1. Test your solution as the following:

   1. you have the `data.json` sample JSON document in your `src/main/resources` folder
   1. define a `main()` method in the `GameRepository` class
   1. instantiate a single instance of the `GameRepository` class to check whether the sample JSON document could be read

#### Implementing the `Queries` interface

Implement the `base.Queries<T, U, V>` interface by the `GameRepository` class, follow the documentation comments:

1. Provide the class `Game` as the value for the parameter `T`.
1. Provide the class `Game.Character` as the value for the parameter `U`.
1. Provide the enum class `Game.Platform` as the value for the parameter `V`.

##### Disclaimer

<div class="alert alert-info">
In this exercise sheet, ChatGPT generated the content of the sample JSON document. Thus, we do not have any responsibility for containing unreal examples.
</div>
