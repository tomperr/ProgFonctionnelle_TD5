package misc

import common._

object Misc {

  /**
   * Exercice 1 Erreur classique
   * Corrige cette fonction
   */
  def fonctionACorriger(list: List[Int], elem: Int): Boolean =
    {
      if (!list.isEmpty)
        list.contains(elem)
      else
        false
    }

  /**
   * The for Loop with yield:
   *  You can store return values from a for loop in a variable or can return through a function.
   *  To do so, you prefix the body of the for expression by the keyword yield as follows:
   *
   *  var retVal =
   *  for{ var x <- List
   *     if condition1;
   *     if condition2...
   *     }
   *     yield x
   *
   * Note the curly braces have been used to keep the variables and conditions and retVal is a variable where all
   * the values of x will be stored in the form of collection.
   * Example:
   * Following is the example to show the usage of for loop along with yield
   *  it produces the following result:
   *        Value of a: 1
   *        Value of a: 2
   *        Value of a: 4
   *        Value of a: 5
   *        Value of a: 6
   *        Value of a: 7
   */

  def main(args: Array[String]) {
    var a = 0
    val numList = List(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)

    // for loop execution with a yield
    var retVal =
      for {
        a <- numList
        if a != 3
        if a < 8
      } yield a

    // Now print returned values using another loop.
    for (a <- retVal) {
      println("Value of a: " + a)
    }
    
    val laura = Person("Laura", false, Nil)
    val bob = Person("Bob", true, Nil)
    val julie = Person("Julie", false, List(laura, bob))
    val john = Person("John", true, List(laura, bob))
    val persons = List(laura, bob, julie, john)  
     
    val prenom = "Julie"
    
    val res = for {
      p <- persons
      c <- p.enfants
      if p.prenom == prenom
    } yield c
    
    println(res)
    
  }

  // E X A M P L E S   B A S I C S

  /**
   * Exercice 2
   * Ecrire une fonction qui renvoie la liste du reste de la divison par 3 des termes d'une liste
   */
  def modulus(myList: List[Int]): List[Int] = {
    for {
        a <- myList
    } yield a % 3 
  }

  /**
   * Exercice 3
   * Ecrire une fonction qui a partir d’un nombre n entier positif, trouve toutes les paires (i, j) telles que
   *    1 <= j <= i <= n
   *    et i + j soit premier
   */
  def isPrime(n: Int) = List.range(2, n).forall(x => n % x != 0)
  
  def primeTuple(n: Int): List[(Int, Int)] = 
  {
    val iList: List[Int] = List.range(1, n)
    for {
      i <- iList
      j <- 1 to i-1
      if isPrime(j+i)
    } yield (i, j)
  }

  // E X A M P L E S   O V E R   S C A L A    A R R A Y

  /**
   * Les case classes fournissent implicitement les methodes suivantes sur les argument du constructeur
   *    toString
   *    equals
   *    hashCode
   *    getter
   */
  case class Person(prenom: String, isMale: Boolean, enfants: List[Person])

  val laura = Person("Laura", false, Nil)
  val bob = Person("Bob", true, Nil)
  val julie = Person("Julie", false, List(laura, bob))
  val john = Person("John", true, List(laura, bob))
  val persons = List(laura, bob, julie, john)

  /**
   * Exercice 4
   * Ecrire une fonction et son test associe qui renvoie la liste des hommes
   */
  def listMale(persons: List[Person]): List[Person] = {
    for {
      p <- persons
      if p.isMale
    } yield p
  }

  /**
   * Exercice 5
   * Ecrire une fonction et son test associe qui renvoie la list des enfants pour une personne donnee
   */
  def childrenOf(persons: List[Person], prenom: String): List[Person] = {
    for {
      p <- persons
      c <- p.enfants
      if p.prenom == prenom
    } yield c
  }

  /**
   * Exercice 6
   * - trouver les titres de tous les livres écrits par Ullman
   * - trouver tous les titres ayant "Program" dans leur titre
   * - trouver les noms de tous les auteurs qui ont écrit au moins deux livres
   */
  case class Book(title: String, authors: List[String])
  val books: List[Book] = List(
    Book("Structure and Interpretation of Computer Programs", List("Abelson, Harold", "Sussman, Gerald J.")),
    Book("Principles of Compiler Design", List("Aho, Alfred", "Ullman, Jeffrey")),
    Book("Programming in Modula-2", List("Wirth, Niklaus")),
    Book("Introduction to Functional Programming", List("Bird, Richard")),
    Book("The Java Language Specification", List("Gosling, James", "Joy, Bill", "Steele, Guy", "Bracha, Gilad")))

  /*
  def getInfos(books: List[Book]) : List[String] = {
    val ullmans = for {
      b <- books
      if b.authors.contains("Ullman")
    } yield b.title
    
    val programs = for {
      b <- books
      if b.title.contains("Program")
    } yield b.title
    
    var passed_aut: List[String] = Nil
    val aut = for {
      b <- books
      passed_aut = passed_aut:::b.authors
     
    }
  }
  */
    
  /**
   * Accès aux éléments d'un n-uplet
   * Vous pouvez accéder aux éléments d'un n-uplet en utilisant une syntaxe de soulignement.
   *  Le premier élément est accessible avec _1,
   *  le second élément avec _2 et ainsi de suite, comme ceci:
   *  val things = ("a", 1, 3.5)
   *  scala> println(things._1)
   *         a
   *  scala> println(things._2)
   *         1
   *  scala> println(things._3)
   *         3.5
   */

  lazy val rates: Map[String, BigDecimal] =
    {
      val url = "http://www.ecb.europa.eu/stats/eurofxref/eurofxref-daily.xml"
      for (e <- xml.XML.load(url) \ "Cube" \ "Cube" \ "Cube")
        yield ((e \ "@currency").text -> BigDecimal((e \ "@rate").text))
    }.toMap ++ Map[String, BigDecimal]("EUR" -> 1)

  /**
   * Exercice 7
   * Afficher toutes les valeurs de la map sous la forme :
   *
   * currency : PLN rate: 4.3148
   * currency : CAD rate: 1.3984
   * currency : HKD rate: 8.2252
   * currency : AUD rate: 1.3807
   * ......
   */
  def printRates(rates: Map[String, BigDecimal]) = ???

  /**
   * Exercice 8
   * convertir la valeur d'une devise en une autre
   */
  def convertRate(amount: BigDecimal, from: String, to: String, rates: Map[String, BigDecimal]) = ???

  /**
   * Exercice 9
   * La conjecture de Goldbach dit que chaque entier positif pair plus grand que 2 est la somme de deux nombres premiers. Par exemple 28 = 5 + 23.
   * C’est l’un des faits les plus connus des mathématiques qui n’a pas encore été prouvé dans le cas général. Il a été confirmé sur des grands nombres
   * (bien plus grands que ce que le type Int de Scala peut représenter).
   * Écrivez une fonction pour trouver les deux nombres premiers qui composent un entier donné.
   *
   * Exemple :
   *
   * scala> goldbach(28)
   * res0: List[(Int, Int)] = List((5,23),(11,17))
   */
  def goldbach(n: Int): List[(Int, Int)] = ???

}

