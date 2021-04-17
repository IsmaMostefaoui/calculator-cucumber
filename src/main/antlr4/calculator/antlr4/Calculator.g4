grammar Calculator;

start: expr? EOF              # Calculation
    | SAVEM expr M NUMBER   # SaveM
    | LOADM  M  NUMBER        # LoadM
    | CONV expr BASE NUMBER        # Convert
    ;


expr:
    STRING                                 # DateOrDuration
    | NUMBER                               # Number
    | NUMBER BASE NUMBER                   # BasedNumber
    | RAND NUMBER                          # Rand
    | LPAREN expr RPAREN                   # Parens
    | NOT expr                             # Not
    | MINUS expr                           # UnaryMinus
    | PLUS expr                            # UnaryPlus
    | expr op=(TIMES|DIV|MOD|MODINV) expr  # MulDiv
    | expr op=(PLUS|MINUS) expr            # AddSub
    | expr EQUIV expr         # Equiv
    | expr IMPLY expr         # Imply
    | expr AND expr                        # And
    | expr XOR expr                        # Xor
    | expr OR expr                         # Or
    ;


BASE: 'base';

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

RAND: 'rand';

EXP: '^';

MODINV : '!%' | 'modinv';

SAVEM: 'savem';
LOADM: 'loadm';

IMPLY: '=>';
EQUIV: '<=>';

HISTORY: 'history';

STRING: '\'' ~'\''* '\''  ;
M: 'M';

CONV: 'conv';

WS   : [ \t\n\r\u000C]+ -> skip ;
