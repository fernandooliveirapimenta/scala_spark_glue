package basico.classes

object CaseClasses extends App{

}

abstract class Tree
case class Sum(a: Tree, b: Tree) extends Tree
case class Var(n: String) extends Tree
case class Const(v: Int) extends Tree
