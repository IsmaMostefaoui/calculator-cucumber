grammar Arithmetic;


expr: expr op=(TIMES|DIV) expr # MulDiv
    | expr op=(PLUS|MINUS) expr # AddSub
    | NUMBER                    # Number
    | LPAREN expr RPAREN             # Parens
    ;






NUMBER: ('0' .. '9')+ ;

LPAREN   : '('   ;

RPAREN   : ')'   ;

PLUS     : '+'   ;

MINUS    : '-'   ;

TIMES    : '*'   ;

DIV      : '/'   ;

POINT    : '.'   ;

