grammar Calculator;

start: expr EOF                                  # Calculation
    | SAVEM expr M NUMBER EOF                    # SaveM
    | LOADM  M  NUMBER EOF                       # LoadM
    | CONV expr BASE NUMBER EOF                  # Convert
    | UNIT_KW NUMBER unit TO unit EOF            # UnitConvert
    | CURR NUMBER IDENTIFIER TO IDENTIFIER EOF   # CurrConvert
    ;


expr: pow                                  # Power
    | expr op=(TIMES|DIV|MOD|MODINV) expr  # MulDiv
    | expr op=(PLUS|MINUS) expr            # AddSub
    | expr EQUIV expr                      # Equiv
    | expr IMPLY expr                      # Imply
    | expr AND expr                        # And
    | expr XOR expr                        # Xor
    | expr OR expr                         # Or
    ;


pow:
    atom (EXP pow)?
    ;
atom : STRING                                # DateOrDuration
      | NUMBER                               # Number
      | NUMBER BASE NUMBER                   # BasedNumber
      | RAND NUMBER                          # Rand
      | LPAREN expr RPAREN                   # Parens
      | NOT expr                             # Not
      | MINUS expr                           # UnaryMinus
      | PLUS expr                            # UnaryPlus
      ;


unit: IDENTIFIER ('^' NUMBER)?;

// keywords
SAVEM: 'savem';
LOADM: 'loadm';
HISTORY: 'history';
MODINV : '!%' | 'modinv';
RAND: 'rand';
CONV: 'conv';
BASE: 'base';
CURR:'curr';
UNIT_KW: 'unit';
M: 'M';
TO: 'to';

// symbols
NUMBER : ('0' .. '9')+ ;

LPAREN : '(' ;

RPAREN : ')' ;

PLUS   : '+' ;

MINUS  : '-' ;

TIMES  : '*' ;

DIV    : '/' ;

AND    : '&';

OR     : '|';

XOR     : '||';

NOT    : '!';

MOD    : '%';


EXP: '^';




IMPLY: '=>';
EQUIV: '<=>';



IDENTIFIER
  :  ('a'..'z' | 'A'..'Z')+
  ;

STRING: '\'' ~'\''* '\''  ;


WS   : [ \t\n\r\u000C]+ -> skip ;
