#### The input

You will read a JSON document containing an array of *course* objects.

##### The object describing a *course*

Each object has the following properties:

1. `code`: the course code of the *course* (JSON type: `string`)
1. `title`: the title of the *course* (JSON type: `string`)
1. `faculty`: the faculty of the *course* (JSON type: `string`)

   This property contains one of the following values:

   1. `"Informatics"`
   1. `"Science"`
   1. `"Engineering"`
   1. `"Economics"`
   1. `"Humanities"`

1. `credits`: the credits of the *course* (JSON type: `number`, contains integers)
1. `semester`: the recommended semester of the *course* (JSON type: `number`, contains integers)
1. `students`: the students of the *course* (JSON type: `array`, contains objects, each element represents a *student*)

##### The object describing a *student*

Each object has the following properties:

1. `neptun`: the Neptun code of the *student* (JSON type: `string`)
1. `name`: the name of the *student* (JSON type: `string`)
1. `grade`: the grade of the *student* (JSON type: `number`, contains integers)

#### Creating the representation

Define the model classes in the package `campus` to represent the given JSON document using *Lombok*. The names of the classes and the fields are mandatory.

1. Create the class `Course` representing a *course*. Follow the given instructions:

   1. Make the class immutable (guarantee that the fields are invisible from outside of the class and cannot be modified, and the class cannot be extended).
   1. Field `faculty` must be represented as an enum constant (see the details later).
   1. Field `students` must be represented as a list.
   1. Generate a constructor having all the parameters. Use the public visibility.
   1. Generate a constructor having no parameter. Use the private visibility.
   1. Generate a getter method for each field. Use the public visibility.
   1. Generate a `toString()` method which contains all the fields except the `students` field.
   1. Two objects are identical if their `code` fields are the same.

1. Create the class `Course.Student` representing a *student* as the public static nested class of the class `Course`. Follow the given instructions:

   1. Make the class immutable (having the common features).
   1. Generate a constructor having all the parameters. Use the public visibility.
   1. Generate a constructor having no parameter. Use the private visibility.
   1. Generate a getter method for each field. Use the public visibility.
   1. Generate a `toString()` method which contains all the fields.
   1. Two objects are identical if their `neptun` fields are the same.

1. Create the enum class `Course.Faculty` representing the possible values of the field `faculty` as the public static nested enum class of the class `Course`.

#### Extending the `Repository` class

You got the abstract `base.Repository<T>` class that is capable of reading a JSON document using the *Jackson* library, and store the objects in the memory.

1. Define the class `CourseRepository` in the package `campus`.
1. Extend the `Repository` class as the following:

   1. provide the class `Course` as its generic type parameter
   1. define a parameterless constructor to support the instantiation of your class

1. Test your solution as the following:

   1. you have the `data.json` sample JSON document in your `src/main/resources` folder
   1. define a `main()` method in the `CourseRepository` class
   1. instantiate a single instance of the `CourseRepository` class to check whether the sample JSON document could be read

#### Implementing the `Queries` interface

Implement the `base.Queries<T, U, V>` interface by the `CourseRepository` class, follow the documentation comments:

1. Provide the class `Course` as the value for the parameter `T`.
1. Provide the class `Course.Student` as the value for the parameter `U`.
1. Provide the enum class `Course.Faculty` as the value for the parameter `V`.

##### Disclaimer

<div class="alert alert-info">
In this exercise sheet, an AI assistant generated the content of the sample JSON document, and the data is fictional. Thus, we do not have any responsibility for containing unreal examples.
</div>
