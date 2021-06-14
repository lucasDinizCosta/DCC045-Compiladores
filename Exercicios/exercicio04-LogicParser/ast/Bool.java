package ast;

/*
 * Esta classe representa um comando de Impressão.
 * Expr
 */
import java.util.HashMap;

public class Bool extends Expr {

      private Boolean l;

      public Bool(int l, int c, String v, Boolean code) {
            super(l, c);
            if (code) {
                  this.l = true;
            } else {
                  this.l = false;
            }
      }

      public Boolean getValue() {
            return l;
      }

      // @Override
      public String toString() {
            return "" + l;
      }

      public Boolean interpret(HashMap<String, Boolean> m) {
            return l;
      }
}
