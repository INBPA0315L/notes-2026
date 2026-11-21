#### The input

You will read a JSON document containing an array of *talk* objects.

##### The object describing a *talk*

Each object has the following properties:

1. `title`: the title of the *talk* (JSON type: `string`)
1. `speaker`: the speaker of the *talk* (JSON type: `string`)
1. `track`: the track of the *talk* (JSON type: `string`)

   This property contains one of the following values:

   1. `"Backend"`
   1. `"Frontend"`
   1. `"Data"`
   1. `"Security"`
   1. `"Cloud"`

1. `duration`: the duration in minutes of the *talk* (JSON type: `number`, contains integers)
1. `rating`: the average rating of the *talk* (JSON type: `number`, contains floating-point numbers)
1. `attendees`: the attendees of the *talk* (JSON type: `array`, contains objects, each element represents an *attendee*)

##### The object describing an *attendee*

Each object has the following properties:

1. `email`: the e-mail address of the *attendee* (JSON type: `string`)
1. `company`: the company of the *attendee* (JSON type: `string`)
1. `age`: the age of the *attendee* (JSON type: `number`, contains integers)

#### Creating the representation

Define the model classes in the package `conference` to represent the given JSON document using *Lombok*. The names of the classes and the fields are mandatory.

1. Create the class `Talk` representing a *talk*. Follow the given instructions:

   1. Make the class immutable (guarantee that the fields are invisible from outside of the class and cannot be modified, and the class cannot be extended).
   1. Field `track` must be represented as an enum constant (see the details later).
   1. Field `attendees` must be represented as an array.
   1. Generate a constructor having all the parameters. Use the public visibility.
   1. Generate a constructor having no parameter. Use the package-level visibility.
   1. Generate a getter method for each field. Use the public visibility.
   1. Generate a `toString()` method which contains all the fields except the `attendees` field.
   1. Two objects are identical if their `title` fields are the same.

1. Create the class `Talk.Attendee` representing an *attendee* as the public static nested class of the class `Talk`. Follow the given instructions:

   1. Make the class immutable (having the common features).
   1. Generate a constructor having all the parameters. Use the public visibility.
   1. Generate a constructor having no parameter. Use the package-level visibility.
   1. Generate a getter method for each field. Use the public visibility.
   1. Generate a `toString()` method which contains all the fields.
   1. Two objects are identical if their `email` fields are the same.

1. Create the enum class `Talk.Track` representing the possible values of the field `track` as the public static nested enum class of the class `Talk`.

#### Extending the `Repository` class

You got the abstract `base.Repository<T>` class that is capable of reading a JSON document using the *Jackson* library, and store the objects in the memory.

1. Define the class `TalkRepository` in the package `conference`.
1. Extend the `Repository` class as the following:

   1. provide the class `Talk` as its generic type parameter
   1. define a parameterless constructor to support the instantiation of your class

1. Test your solution as the following:

   1. you have the `data.json` sample JSON document in your `src/main/resources` folder
   1. define a `main()` method in the `TalkRepository` class
   1. instantiate a single instance of the `TalkRepository` class to check whether the sample JSON document could be read

#### Implementing the `Queries` interface

Implement the `base.Queries<T, U, V>` interface by the `TalkRepository` class, follow the documentation comments:

1. Provide the class `Talk` as the value for the parameter `T`.
1. Provide the class `Talk.Attendee` as the value for the parameter `U`.
1. Provide the enum class `Talk.Track` as the value for the parameter `V`.

##### Disclaimer

<div class="alert alert-info">
In this exercise sheet, an AI assistant generated the content of the sample JSON document, and the data is fictional. Thus, we do not have any responsibility for containing unreal examples.
</div>
