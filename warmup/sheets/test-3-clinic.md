#### The input

You will read a JSON document containing an array of *doctor* objects.

##### The object describing a *doctor*

Each object has the following properties:

1. `name`: the name of the *doctor* (JSON type: `string`)
1. `department`: the department of the *doctor* (JSON type: `string`)

   This property contains one of the following values:

   1. `"Cardiology"`
   1. `"Neurology"`
   1. `"Pediatrics"`
   1. `"Orthopedics"`
   1. `"Dermatology"`

1. `experience`: the years of experience of the *doctor* (JSON type: `number`, contains integers)
1. `room`: the room number of the *doctor* (JSON type: `string`)
1. `onCall`: the on-call status of the *doctor* (JSON type: `boolean`)
1. `patients`: the patients of the *doctor* (JSON type: `array`, contains objects, each element represents a *patient*)

##### The object describing a *patient*

Each object has the following properties:

1. `insuranceNumber`: the insurance number of the *patient* (JSON type: `number`, contains integers)
1. `name`: the name of the *patient* (JSON type: `string`)
1. `age`: the age of the *patient* (JSON type: `number`, contains integers)

#### Creating the representation

Define the model classes in the package `clinic` to represent the given JSON document using *Lombok*. The names of the classes and the fields are mandatory.

1. Create the class `Doctor` representing a *doctor*. Follow the given instructions:

   1. Guarantee that all the fields are accessible only from the same package and derived classes.
   1. Prevent other classes from being derived from this class.
   1. Guarantee that the fields cannot be modified after instantiation.
   1. Field `department` must be represented as an enum constant (see the details later).
   1. Field `patients` must be represented as a list.
   1. Generate a constructor having all the parameters. Use the public visibility.
   1. Generate a constructor having no parameter. Use the private visibility.
   1. Generate a getter method for each field. Use the public visibility.
   1. Generate a `toString()` method which contains all the fields except the `patients` field.
   1. Two objects are identical if their `name` fields are the same.

1. Create the class `Doctor.Patient` representing a *patient* as the public static nested class of the class `Doctor`. Follow the given instructions:

   1. Guarantee that all the fields are accessible only from the same package and derived classes.
   1. Prevent other classes from being derived from this class.
   1. Guarantee that the fields cannot be modified after instantiation.
   1. Generate a constructor having all the parameters. Use the public visibility.
   1. Generate a constructor having no parameter. Use the private visibility.
   1. Generate a getter method for each field. Use the public visibility.
   1. Generate a `toString()` method which contains all the fields.
   1. Two objects are identical if their `insuranceNumber` fields are the same.

1. Create the enum class `Doctor.Department` representing the possible values of the field `department` as the public static nested enum class of the class `Doctor`.

#### Extending the `Repository` class

You got the abstract `base.Repository<T>` class that is capable of reading a JSON document using the *Jackson* library, and store the objects in the memory.

1. Define the class `DoctorRepository` in the package `clinic`.
1. Extend the `Repository` class as the following:

   1. provide the class `Doctor` as its generic type parameter
   1. define a parameterless constructor to support the instantiation of your class

1. Test your solution as the following:

   1. you have the `data.json` sample JSON document in your `src/main/resources` folder
   1. define a `main()` method in the `DoctorRepository` class
   1. instantiate a single instance of the `DoctorRepository` class to check whether the sample JSON document could be read

#### Implementing the `Queries` interface

Implement the `base.Queries<T, U, V>` interface by the `DoctorRepository` class, follow the documentation comments:

1. Provide the class `Doctor` as the value for the parameter `T`.
1. Provide the class `Doctor.Patient` as the value for the parameter `U`.
1. Provide the enum class `Doctor.Department` as the value for the parameter `V`.

##### Disclaimer

<div class="alert alert-info">
In this exercise sheet, an AI assistant generated the content of the sample JSON document, and the data is fictional. Thus, we do not have any responsibility for containing unreal examples.
</div>
