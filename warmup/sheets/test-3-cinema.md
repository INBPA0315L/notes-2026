#### The input

You will read a JSON document containing an array of *movie* objects.

##### The object describing a *movie*

Each object has the following properties:

1. `title`: the title of the *movie* (JSON type: `string`)
1. `genre`: the genre of the *movie* (JSON type: `string`)
1. `studio`: the studio of the *movie* (JSON type: `string`)

   This property contains one of the following values:

   1. `"Paramount Pictures"`
   1. `"Universal Pictures"`
   1. `"Columbia Pictures"`
   1. `"Warner Bros."`

1. `year`: the release year of the *movie* (JSON type: `number`, contains integers)
1. `actors`: the actors of the *movie* (JSON type: `array`, contains objects, each element represents an *actor*)

##### The object describing an *actor*

Each object has the following properties:

1. `name`: the name of the *actor* (JSON type: `string`)
1. `nationality`: the nationality of the *actor* (JSON type: `string`)
1. `age`: the age of the *actor* (JSON type: `number`, contains integers)

#### Creating the representation

Define the model classes in the package `cinema` to represent the given JSON document using *Lombok*. The names of the classes and the fields are mandatory.

1. Create the class `Movie` representing a *movie*. Follow the given instructions:

   1. Guarantee that all the fields are accessible only from the same package.
   1. Prevent other classes from being derived from this class.
   1. Guarantee that the fields cannot be modified after instantiation.
   1. Field `studio` must be represented as an enum constant (see the details later).
   1. Field `actors` must be represented as a list.
   1. Generate a constructor having all the parameters. Use the public visibility.
   1. Generate a constructor having no parameter. Use the protected visibility.
   1. Generate a getter method for each field. Use the public visibility.
   1. Generate a `toString()` method which contains all the fields except the `actors` field.
   1. Two objects are identical if their `title` and `year` fields are the same.

1. Create the class `Movie.Actor` representing an *actor* as the public static nested class of the class `Movie`. Follow the given instructions:

   1. Define all the fields having private visibility.
   1. Generate a constructor having all the parameters. Use the public visibility.
   1. Generate a constructor having no parameter. Use the protected visibility.
   1. Generate a getter method for each field. Use the public visibility.
   1. Generate a `toString()` method which contains only the `name` field.
   1. Two objects are identical if their `name` fields are the same.

1. Create the enum class `Movie.Studio` representing the possible values of the field `studio` as the public static nested enum class of the class `Movie`.

#### Extending the `Repository` class

You got the abstract `base.Repository<T>` class that is capable of reading a JSON document using the *Jackson* library, and store the objects in the memory.

1. Define the class `MovieRepository` in the package `cinema`.
1. Extend the `Repository` class as the following:

   1. provide the class `Movie` as its generic type parameter
   1. define a parameterless constructor to support the instantiation of your class

1. Test your solution as the following:

   1. you have the `data.json` sample JSON document in your `src/main/resources` folder
   1. define a `main()` method in the `MovieRepository` class
   1. instantiate a single instance of the `MovieRepository` class to check whether the sample JSON document could be read

#### Implementing the `Queries` interface

Implement the `base.Queries<T, U, V>` interface by the `MovieRepository` class, follow the documentation comments:

1. Provide the class `Movie` as the value for the parameter `T`.
1. Provide the class `Movie.Actor` as the value for the parameter `U`.
1. Provide the enum class `Movie.Studio` as the value for the parameter `V`.

##### Disclaimer

<div class="alert alert-info">
In this exercise sheet, ChatGPT generated the content of the sample JSON document. Thus, we do not have any responsibility for containing unreal examples.
</div>
