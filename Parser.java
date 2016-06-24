//### This file created by BYACC 1.8(/Java extension  1.15)
//### Java capabilities added 7 Jan 97, Bob Jamison
//### Updated : 27 Nov 97  -- Bob Jamison, Joe Nieten
//###           01 Jan 98  -- Bob Jamison -- fixed generic semantic constructor
//###           01 Jun 99  -- Bob Jamison -- added Runnable support
//###           06 Aug 00  -- Bob Jamison -- made state variables class-global
//###           03 Jan 01  -- Bob Jamison -- improved flags, tracing
//###           16 May 01  -- Bob Jamison -- added custom stack sizing
//###           04 Mar 02  -- Yuval Oren  -- improved java performance, added options
//###           14 Mar 02  -- Tomas Hurka -- -d support, static initializer workaround
//### Please send bug reports to tom@hukatronic.cz
//### static char yysccsid[] = "@(#)yaccpar	1.8 (Berkeley) 01/20/90";






//#line 3 "exemploSem.y"
  /*Bruno Dorscheidt Brandelli, 122019003 João Vicente 11180565, João Berte 14280223*/
  import java.io.*;
  import java.util.ArrayList;
//#line 21 "Parser.java"




public class Parser
{

boolean yydebug;        //do I want debug output?
int yynerrs;            //number of errors so far
int yyerrflag;          //was there an error?
int yychar;             //the current working character

//########## MESSAGES ##########
//###############################################################
// method: debug
//###############################################################
void debug(String msg)
{
  if (yydebug)
    System.out.println(msg);
}

//########## STATE STACK ##########
final static int YYSTACKSIZE = 500;  //maximum stack size
int statestk[] = new int[YYSTACKSIZE]; //state stack
int stateptr;
int stateptrmax;                     //highest index of stackptr
int statemax;                        //state when highest index reached
//###############################################################
// methods: state stack push,pop,drop,peek
//###############################################################
final void state_push(int state)
{
  try {
		stateptr++;
		statestk[stateptr]=state;
	 }
	 catch (ArrayIndexOutOfBoundsException e) {
     int oldsize = statestk.length;
     int newsize = oldsize * 2;
     int[] newstack = new int[newsize];
     System.arraycopy(statestk,0,newstack,0,oldsize);
     statestk = newstack;
     statestk[stateptr]=state;
  }
}
final int state_pop()
{
  return statestk[stateptr--];
}
final void state_drop(int cnt)
{
  stateptr -= cnt; 
}
final int state_peek(int relative)
{
  return statestk[stateptr-relative];
}
//###############################################################
// method: init_stacks : allocate and prepare stacks
//###############################################################
final boolean init_stacks()
{
  stateptr = -1;
  val_init();
  return true;
}
//###############################################################
// method: dump_stacks : show n levels of the stacks
//###############################################################
void dump_stacks(int count)
{
int i;
  System.out.println("=index==state====value=     s:"+stateptr+"  v:"+valptr);
  for (i=0;i<count;i++)
    System.out.println(" "+i+"    "+statestk[i]+"      "+valstk[i]);
  System.out.println("======================");
}


//########## SEMANTIC VALUES ##########
//public class ParserVal is defined in ParserVal.java


String   yytext;//user variable to return contextual strings
ParserVal yyval; //used to return semantic vals from action routines
ParserVal yylval;//the 'lval' (result) I got from yylex()
ParserVal valstk[];
int valptr;
//###############################################################
// methods: value stack push,pop,drop,peek.
//###############################################################
void val_init()
{
  valstk=new ParserVal[YYSTACKSIZE];
  yyval=new ParserVal();
  yylval=new ParserVal();
  valptr=-1;
}
void val_push(ParserVal val)
{
  if (valptr>=YYSTACKSIZE)
    return;
  valstk[++valptr]=val;
}
ParserVal val_pop()
{
  if (valptr<0)
    return new ParserVal();
  return valstk[valptr--];
}
void val_drop(int cnt)
{
int ptr;
  ptr=valptr-cnt;
  if (ptr<0)
    return;
  valptr = ptr;
}
ParserVal val_peek(int relative)
{
int ptr;
  ptr=valptr-relative;
  if (ptr<0)
    return new ParserVal();
  return valstk[ptr];
}
final ParserVal dup_yyval(ParserVal val)
{
  ParserVal dup = new ParserVal();
  dup.ival = val.ival;
  dup.dval = val.dval;
  dup.sval = val.sval;
  dup.obj = val.obj;
  return dup;
}
//#### end semantic value section ####
public final static short IDENT=257;
public final static short INT=258;
public final static short BOOL=259;
public final static short NUM=260;
public final static short STRING=261;
public final static short DOUBLE=262;
public final static short NEW=263;
public final static short TRUE=264;
public final static short FALSE=265;
public final static short LITERAL=266;
public final static short AND=267;
public final static short VOID=268;
public final static short MAIN=269;
public final static short IF=270;
public final static short CLASSE=271;
public final static short WHILE=272;
public final static short ENDWHILE=273;
public final static short FOR=274;
public final static short ENDFOR=275;
public final static short ESCREVA=276;
public final static short LEIA=277;
public final static short STRUCT=278;
public final static short PRIVATE=279;
public final static short PUBLIC=280;
public final static short OR=281;
public final static short GREATEREQUAL=282;
public final static short LESSEREQUAL=283;
public final static short RETORNO=284;
public final static short ENDIF=285;
public final static short ELSE=286;
public final static short BREAK=287;
public final static short YYERRCODE=256;
final static short yylhs[] = {                           -1,
    4,    0,    5,    3,    6,    7,   11,    8,    8,   10,
   12,   12,    1,    1,    1,    1,    1,   14,    9,   13,
   13,   17,   19,   16,   21,   22,   16,   23,   24,   16,
   25,   26,   16,   16,   28,   18,   18,   30,   29,   29,
   15,   15,   27,   20,   33,   32,   31,   31,   35,   35,
   35,   34,   36,   34,   34,   34,   34,   34,   37,   37,
   43,   40,   41,   42,   42,   44,   38,   45,   46,   39,
    2,    2,    2,    2,    2,    2,    2,    2,    2,    2,
    2,    2,    2,    2,    2,    2,    2,    2,    2,   47,
   48,   48,   49,   49,
};
final static short yylen[] = {                            2,
    0,    2,    0,    7,    2,    2,    0,    4,    0,    3,
    3,    0,    1,    1,    1,    1,    1,    0,    3,    2,
    0,    0,    0,    9,    0,    0,    9,    0,    0,    9,
    0,    0,    9,    5,    0,    4,    0,    0,    5,    0,
    6,    0,    3,    4,    0,    4,    2,    0,    2,    1,
    0,    2,    0,    7,    1,    1,    3,    3,    6,    0,
    0,    3,    1,    2,    0,    0,    6,    0,    0,   14,
    3,    3,    3,    3,    3,    3,    3,    3,    3,    3,
    1,    1,    1,    1,    3,    1,    1,    3,    1,    6,
    2,    0,    3,    0,
};
final static short yydefred[] = {                         1,
    0,    0,    0,    2,    3,    0,    0,    0,    0,   17,
   13,   14,   16,   15,    7,    5,   18,    0,    0,    6,
   21,    4,    0,    0,    0,    0,    0,    8,    0,    0,
    0,    0,    0,    0,   19,   20,    0,   10,    0,   22,
   25,   31,   28,    0,   11,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,   48,
   34,    0,    0,    0,    0,    0,    0,    0,   36,    0,
   23,   26,   32,   29,   41,    0,   83,   84,   81,   82,
   86,    0,    0,    0,    0,    0,   43,    0,    0,   47,
   55,   56,   89,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,   52,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,   57,
   58,   85,   77,   78,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,   48,   24,   27,   33,   30,    0,
   48,    0,    0,    0,   62,   39,    0,    0,    0,    0,
   50,    0,    0,    0,    0,    0,    0,   91,   90,    0,
    0,   67,   49,    0,    0,   44,    0,    0,   54,    0,
    0,   93,    0,    0,   46,   48,    0,    0,    0,   59,
    0,    0,    0,   70,
};
final static short yydgoto[] = {                          1,
   15,   89,    4,    2,    6,    9,   18,   16,   20,   24,
   19,   27,   25,   21,   35,   36,   48,   46,   95,  146,
   49,   96,   51,   98,   50,   97,   61,   47,   69,   70,
   68,  166,  181,   90,  162,  126,  171,   91,   92,  104,
  106,  155,  129,  127,  163,  184,   93,  159,  168,
};
final static short yysindex[] = {                         0,
    0, -266, -245,    0,    0, -116, -265, -225, -262,    0,
    0,    0,    0,    0,    0,    0,    0, -105, -228,    0,
    0,    0,   -9, -225, -197, -219,   -3,    0,   17, -198,
 -194, -179, -169, -180,    0,    0,   -9,    0,    0,    0,
    0,    0,    0,   55,    0,   57, -225,   62,   63,   64,
   65,   66,  -17, -149,    0,    0,    0,    0, -225,    0,
    0,    0,   68,   69,   70,   71,  -17,  241,    0,   75,
    0,    0,    0,    0,    0,   67,    0,    0,    0,    0,
    0,  176,  176, -135,  176,  176,    0,  176,  107,    0,
    0,    0,    0, -225, -225, -225, -225, -225, -131,  140,
  140,   73,  140,   76,  140,   77,   86,  176,  176,  176,
  176,  176,  176,  176,  176,  176,  176,  176,    0, -127,
    9,    9,    9,    9,   97,   87,  516,  176,  100,    0,
    0,    0,    0,    0,  -39,  -39,  140,  -39,  -39,  -32,
  -32, -250, -250,    0,    0,    0,    0,    0,    0,  176,
    0, -136,  140,  176,    0,    0,  -40,  113,  112,  267,
    0,  285,  102,  140,  176,   37,  176,    0,    0, -123,
 -120,    0,    0,  176,  140,    0,  113,  106,    0,  140,
  111,    0,  516,  121,    0,    0,  176,  342,  134,    0,
  516, -136,  455,    0,
};
final static short yyrindex[] = {                         0,
    0,    0,    0,    0,    0,    0,    0, -109,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,  125, -121,   47,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,  125,    0,  225,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,  225,  225,  225,  225,   74,    0,
    0,  -14,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,  -19,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,   74,   74,   74,   74,    0,  128,
  534,    0,  -43,    0,  129,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,  130,    0,
    0,    0,    0,    0,  320,  368,  434,  390,  412,   56,
   80,    8,   32,  -14,    0,    0,    0,    0,    0,  149,
    0,  476,  132,    0,    0,    0,    0,  152,    0,  -87,
    0,    0,    0,  144,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,  145,    0,  152,    0,    0,  146,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,  494,    0,    0,
};
final static short yygindex[] = {                         0,
  -36,  730,    0,    0,    0,    0,    0,  -15,    0,    0,
    0,  169,    0,    0,    0,    0,    0,   29,    0,  -77,
    0,    0,    0,    0,    0,    0,  141,    0,   79,    0,
 -132,    0,    0,   16,   18,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,   34,
};
final static int YYTABLESIZE=917;
static short yytable[];
static { yytable();}
static void yytable(){
yytable = new short[]{                         88,
   61,    9,  118,  115,    3,  116,    7,  117,   28,  118,
   54,    5,  157,    8,  117,   61,  108,   17,  160,   22,
   87,   87,   87,   87,   87,   87,   40,   87,   23,   38,
  109,   10,   11,   12,   26,   13,   14,   37,   87,   87,
   87,   87,   87,   67,  147,  148,  149,   74,   74,   74,
   74,   74,   74,  188,   74,   38,   39,  120,   40,   29,
   30,   31,   41,   32,   33,   74,   74,   74,   74,   74,
   34,   72,   72,   72,   72,   72,   72,   42,   72,  121,
  122,  123,  124,   63,   64,   65,   66,   43,   44,   72,
   72,   72,   72,   72,   52,   71,   71,   53,   71,   71,
   71,   55,   56,   57,   58,   60,   59,   62,   71,   72,
   73,   74,   99,   71,   71,   71,   71,   71,   94,   73,
   73,  102,   73,   73,   73,  125,  132,  118,  115,  144,
  116,  145,  117,  128,  130,  131,  150,   73,   73,   73,
   73,   73,  152,  154,  151,  114,  112,  113,  118,  115,
  161,  116,  169,  117,  118,  115,  167,  116,    9,  117,
  174,  176,  178,  183,  179,  119,  114,  112,  113,  185,
    9,   42,  114,  112,  113,  118,  115,  173,  116,  187,
  117,  118,  115,   12,  116,   53,  117,   63,   65,   92,
   68,  191,   94,  114,  112,  113,    9,   60,  186,  114,
  112,  113,   64,   45,   69,   45,  192,   75,  173,  193,
  182,    0,    0,    0,    0,   88,   76,    0,    0,   77,
    0,   78,  156,   79,   80,   81,    0,  108,    0,   82,
    0,   83,    0,   84,  108,   85,   86,   87,    0,    0,
   87,  109,   87,  165,   87,   87,   87,   87,  109,    0,
   87,    0,   87,    0,   87,    0,   87,   87,    0,    0,
    0,   87,   87,   87,   74,   37,    0,   74,    0,   74,
    0,   74,   74,   74,    0,    0,    0,   74,    0,   74,
   88,   74,    0,   74,   74,    0,    0,    0,   72,   74,
   74,   72,    0,   72,    0,   72,   72,   72,    0,    0,
    0,   72,    0,   72,    0,   72,   88,   72,   72,    0,
    0,    0,   71,   72,   72,   71,    0,   71,    0,   71,
   71,   71,    0,    0,   88,   71,    0,   71,    0,   71,
    0,   71,   71,    0,    0,    0,   73,   71,   71,   73,
    0,   73,    0,   73,   73,   73,    0,    0,    0,   73,
    0,   73,  108,   73,    0,   73,   73,  170,    0,   80,
   80,   73,   73,   80,    0,   87,  109,  110,  111,    0,
    0,    0,    0,  108,    0,    0,    0,   80,   80,  108,
   80,   88,    0,    0,    0,    0,    0,  109,  110,  111,
    0,    0,    0,  109,  110,  111,    0,    0,    0,    0,
  108,    0,    0,    0,    0,    0,  108,   79,   79,    0,
    0,   79,    0,    0,  109,  110,  111,    0,    0,    0,
  109,  110,  111,    0,    0,   79,   79,    0,   79,   75,
   75,    0,   76,   75,  190,   77,    0,   78,    0,   79,
   80,   81,    0,    0,    0,    0,    0,   75,   75,    0,
   75,   76,   76,    0,    0,   76,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,   76,
   76,    0,   76,   88,   88,    0,    0,   88,    0,    0,
    0,   35,   35,   35,    0,   35,   35,    0,    0,    0,
    0,   88,   88,    0,   88,    0,    0,   76,    0,    0,
   77,    0,   78,    0,   79,   80,   81,    0,    0,    0,
   82,    0,   83,    0,   84,   51,   85,   86,    0,    0,
    0,    0,    0,   76,    0,    0,   77,    0,   78,    0,
   79,   80,   81,   51,    0,    0,   82,    0,   83,    0,
   84,   76,   85,   86,   77,    0,   78,    0,   79,   80,
   81,    0,    0,    0,   82,   88,   83,  172,   84,    0,
   85,   86,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,   66,    0,    0,   80,    0,    0,   80,
    0,   80,    0,   80,   80,   80,    0,    0,    0,   80,
    0,   80,    0,   80,    0,   80,   80,    0,   76,    0,
    0,   77,    0,   78,    0,   79,   80,   81,    0,    0,
    0,   82,    0,   83,    0,   84,    0,   85,   86,    0,
    0,    0,    0,    0,   79,    0,    0,   79,    0,   79,
    0,   79,   79,   79,    0,    0,    0,   79,    0,   79,
    0,   79,    0,   79,   79,    0,   75,    0,    0,   75,
    0,   75,    0,   75,   75,   75,    0,    0,    0,   75,
    0,   75,    0,   75,    0,   75,   75,    0,   76,    0,
    0,   76,    0,   76,    0,   76,   76,   76,    0,    0,
    0,   76,    0,   76,    0,   76,    0,   76,   76,    0,
   88,    0,    0,   88,    0,   88,    0,   88,   88,   88,
    0,    0,    0,   88,    0,   88,    0,   88,    0,   88,
   88,   76,    0,    0,   77,    0,   78,    0,   79,   80,
   81,    0,    0,    0,   82,    0,   83,    0,   84,  194,
   85,   86,   51,    0,    0,   51,    0,   51,    0,   51,
   51,   51,    0,    0,    0,   51,    0,   51,   51,   51,
   51,   51,   51,   51,    0,   51,    0,   51,   51,   51,
    0,    0,    0,   51,    0,   51,    0,   51,   51,   51,
   51,    0,   76,    0,    0,   77,    0,   78,    0,   79,
   80,   81,    0,    0,    0,   82,    0,   83,    0,   84,
   66,   85,   86,   66,    0,   66,    0,   66,   66,   66,
    0,    0,    0,   66,    0,   66,    0,   66,    0,   66,
   66,  100,  101,    0,  103,  105,    0,  107,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,  133,  134,  135,
  136,  137,  138,  139,  140,  141,  142,  143,    0,    0,
    0,    0,    0,    0,    0,    0,    0,  153,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,  158,
    0,    0,    0,  164,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,  175,    0,  177,    0,    0,    0,
    0,    0,    0,  180,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,  189,
};
}
static short yycheck[];
static { yycheck(); }
static void yycheck() {
yycheck = new short[] {                         40,
   44,  123,   42,   43,  271,   45,  123,   47,   24,   42,
   47,  257,  145,  279,   47,   59,  267,  280,  151,  125,
   40,   41,   42,   43,   44,   45,   41,   47,  257,   44,
  281,  257,  258,  259,   44,  261,  262,  257,   58,   59,
   60,   61,   62,   59,  122,  123,  124,   40,   41,   42,
   43,   44,   45,  186,   47,   59,   40,   94,  257,  257,
  258,  259,  257,  261,  262,   58,   59,   60,   61,   62,
  268,   40,   41,   42,   43,   44,   45,  257,   47,   95,
   96,   97,   98,   55,   56,   57,   58,  257,  269,   58,
   59,   60,   61,   62,   40,   40,   41,   41,   43,   44,
   45,   40,   40,   40,   40,  123,   41,  257,   41,   41,
   41,   41,   46,   58,   59,   60,   61,   62,   44,   40,
   41,  257,   43,   44,   45,  257,   41,   42,   43,  257,
   45,  123,   47,   61,   59,   59,   40,   58,   59,   60,
   61,   62,  127,   44,   58,   60,   61,   62,   42,   43,
  287,   45,   41,   47,   42,   43,   44,   45,  280,   47,
   59,  125,  286,   58,  285,   59,   60,   61,   62,   59,
  280,  125,   60,   61,   62,   42,   43,  162,   45,   59,
   47,   42,   43,   59,   45,   58,   47,   59,   59,   41,
   59,   58,   41,   60,   61,   62,  123,  285,  183,   60,
   61,   62,   59,   59,   59,   37,  191,   67,  193,  192,
  177,   -1,   -1,   -1,   -1,   40,  257,   -1,   -1,  260,
   -1,  262,  144,  264,  265,  266,   -1,  267,   -1,  270,
   -1,  272,   -1,  274,  267,  276,  277,  257,   -1,   -1,
  260,  281,  262,  284,  264,  265,  266,  267,  281,   -1,
  270,   -1,  272,   -1,  274,   -1,  276,  277,   -1,   -1,
   -1,  281,  282,  283,  257,   41,   -1,  260,   -1,  262,
   -1,  264,  265,  266,   -1,   -1,   -1,  270,   -1,  272,
   40,  274,   -1,  276,  277,   -1,   -1,   -1,  257,  282,
  283,  260,   -1,  262,   -1,  264,  265,  266,   -1,   -1,
   -1,  270,   -1,  272,   -1,  274,   40,  276,  277,   -1,
   -1,   -1,  257,  282,  283,  260,   -1,  262,   -1,  264,
  265,  266,   -1,   -1,   40,  270,   -1,  272,   -1,  274,
   -1,  276,  277,   -1,   -1,   -1,  257,  282,  283,  260,
   -1,  262,   -1,  264,  265,  266,   -1,   -1,   -1,  270,
   -1,  272,  267,  274,   -1,  276,  277,   91,   -1,   40,
   41,  282,  283,   44,   -1,  125,  281,  282,  283,   -1,
   -1,   -1,   -1,  267,   -1,   -1,   -1,   58,   59,  267,
   61,   40,   -1,   -1,   -1,   -1,   -1,  281,  282,  283,
   -1,   -1,   -1,  281,  282,  283,   -1,   -1,   -1,   -1,
  267,   -1,   -1,   -1,   -1,   -1,  267,   40,   41,   -1,
   -1,   44,   -1,   -1,  281,  282,  283,   -1,   -1,   -1,
  281,  282,  283,   -1,   -1,   58,   59,   -1,   61,   40,
   41,   -1,  257,   44,   93,  260,   -1,  262,   -1,  264,
  265,  266,   -1,   -1,   -1,   -1,   -1,   58,   59,   -1,
   61,   40,   41,   -1,   -1,   44,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   58,
   59,   -1,   61,   40,   41,   -1,   -1,   44,   -1,   -1,
   -1,  257,  258,  259,   -1,  261,  262,   -1,   -1,   -1,
   -1,   58,   59,   -1,   40,   -1,   -1,  257,   -1,   -1,
  260,   -1,  262,   -1,  264,  265,  266,   -1,   -1,   -1,
  270,   -1,  272,   -1,  274,   40,  276,  277,   -1,   -1,
   -1,   -1,   -1,  257,   -1,   -1,  260,   -1,  262,   -1,
  264,  265,  266,   40,   -1,   -1,  270,   -1,  272,   -1,
  274,  257,  276,  277,  260,   -1,  262,   -1,  264,  265,
  266,   -1,   -1,   -1,  270,   40,  272,  273,  274,   -1,
  276,  277,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   40,   -1,   -1,  257,   -1,   -1,  260,
   -1,  262,   -1,  264,  265,  266,   -1,   -1,   -1,  270,
   -1,  272,   -1,  274,   -1,  276,  277,   -1,  257,   -1,
   -1,  260,   -1,  262,   -1,  264,  265,  266,   -1,   -1,
   -1,  270,   -1,  272,   -1,  274,   -1,  276,  277,   -1,
   -1,   -1,   -1,   -1,  257,   -1,   -1,  260,   -1,  262,
   -1,  264,  265,  266,   -1,   -1,   -1,  270,   -1,  272,
   -1,  274,   -1,  276,  277,   -1,  257,   -1,   -1,  260,
   -1,  262,   -1,  264,  265,  266,   -1,   -1,   -1,  270,
   -1,  272,   -1,  274,   -1,  276,  277,   -1,  257,   -1,
   -1,  260,   -1,  262,   -1,  264,  265,  266,   -1,   -1,
   -1,  270,   -1,  272,   -1,  274,   -1,  276,  277,   -1,
  257,   -1,   -1,  260,   -1,  262,   -1,  264,  265,  266,
   -1,   -1,   -1,  270,   -1,  272,   -1,  274,   -1,  276,
  277,  257,   -1,   -1,  260,   -1,  262,   -1,  264,  265,
  266,   -1,   -1,   -1,  270,   -1,  272,   -1,  274,  275,
  276,  277,  257,   -1,   -1,  260,   -1,  262,   -1,  264,
  265,  266,   -1,   -1,   -1,  270,   -1,  272,  273,  274,
  257,  276,  277,  260,   -1,  262,   -1,  264,  265,  266,
   -1,   -1,   -1,  270,   -1,  272,   -1,  274,  275,  276,
  277,   -1,  257,   -1,   -1,  260,   -1,  262,   -1,  264,
  265,  266,   -1,   -1,   -1,  270,   -1,  272,   -1,  274,
  257,  276,  277,  260,   -1,  262,   -1,  264,  265,  266,
   -1,   -1,   -1,  270,   -1,  272,   -1,  274,   -1,  276,
  277,   82,   83,   -1,   85,   86,   -1,   88,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,  108,  109,  110,
  111,  112,  113,  114,  115,  116,  117,  118,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,  128,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,  150,
   -1,   -1,   -1,  154,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,  165,   -1,  167,   -1,   -1,   -1,
   -1,   -1,   -1,  174,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,  187,
};
}
final static short YYFINAL=1;
final static short YYMAXTOKEN=287;
final static String yyname[] = {
"end-of-file",null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,"'('","')'","'*'","'+'","','",
"'-'","'.'","'/'",null,null,null,null,null,null,null,null,null,null,"':'","';'",
"'<'","'='","'>'",null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
"'['",null,"']'",null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,"'{'",null,"'}'",null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,"IDENT","INT","BOOL","NUM","STRING",
"DOUBLE","NEW","TRUE","FALSE","LITERAL","AND","VOID","MAIN","IF","CLASSE",
"WHILE","ENDWHILE","FOR","ENDFOR","ESCREVA","LEIA","STRUCT","PRIVATE","PUBLIC",
"OR","GREATEREQUAL","LESSEREQUAL","RETORNO","ENDIF","ELSE","BREAK",
};
final static String yyrule[] = {
"$accept : prog",
"$$1 :",
"prog : $$1 clas",
"$$2 :",
"clas : CLASSE IDENT $$2 '{' privados publicos '}'",
"privados : PRIVATE dList",
"publicos : PUBLIC mList",
"$$3 :",
"dList : type $$3 decl dList",
"dList :",
"decl : IDENT moreDecl ';'",
"moreDecl : ',' IDENT moreDecl",
"moreDecl :",
"type : INT",
"type : BOOL",
"type : DOUBLE",
"type : STRING",
"type : IDENT",
"$$4 :",
"mList : $$4 blocos main",
"blocos : blocos bl",
"blocos :",
"$$5 :",
"$$6 :",
"bl : INT IDENT $$5 '(' parametros ')' $$6 dList bloco",
"$$7 :",
"$$8 :",
"bl : BOOL IDENT $$7 '(' parametros ')' $$8 dList bloco",
"$$9 :",
"$$10 :",
"bl : DOUBLE IDENT $$9 '(' parametros ')' $$10 dList bloco",
"$$11 :",
"$$12 :",
"bl : STRING IDENT $$11 '(' parametros ')' $$12 dList bloco",
"bl : IDENT '(' parametros ')' blocoConstrutor",
"$$13 :",
"parametros : $$13 type IDENT lParametros",
"parametros :",
"$$14 :",
"lParametros : $$14 ',' type IDENT lParametros",
"lParametros :",
"main : VOID MAIN '(' ')' dList blocoConstrutor",
"main :",
"blocoConstrutor : '{' listacmd '}'",
"bloco : '{' listacmd retorno '}'",
"$$15 :",
"retorno : RETORNO exp $$15 ';'",
"listacmd : listacmd cmd",
"listacmd :",
"listacmdrep : listacmdrep cmd",
"listacmdrep : BREAK",
"listacmdrep :",
"cmd : exp ';'",
"$$16 :",
"cmd : IF exp $$16 ':' listacmd else ENDIF",
"cmd : while",
"cmd : for",
"cmd : ESCREVA escreva ';'",
"cmd : LEIA leia ';'",
"else : '[' ELSE ':' cmd listacmd ']'",
"else :",
"$$17 :",
"escreva : exp $$17 parametroEscreva",
"leia : exp",
"parametroEscreva : ',' exp",
"parametroEscreva :",
"$$18 :",
"while : WHILE exp $$18 cmd listacmdrep ENDWHILE",
"$$19 :",
"$$20 :",
"for : FOR IDENT '=' exp $$19 ';' exp $$20 ';' exp ':' cmd listacmdrep ENDFOR",
"exp : exp '+' exp",
"exp : exp '*' exp",
"exp : exp '-' exp",
"exp : exp '/' exp",
"exp : exp '>' exp",
"exp : exp '<' exp",
"exp : exp AND exp",
"exp : exp OR exp",
"exp : exp LESSEREQUAL exp",
"exp : exp GREATEREQUAL exp",
"exp : TRUE",
"exp : FALSE",
"exp : NUM",
"exp : DOUBLE",
"exp : '(' exp ')'",
"exp : LITERAL",
"exp : IDENT",
"exp : exp '=' exp",
"exp : metodo",
"metodo : IDENT '.' IDENT '(' parametrosMetodo ')'",
"parametrosMetodo : exp lParametrosMetodo",
"parametrosMetodo :",
"lParametrosMetodo : ',' exp lParametrosMetodo",
"lParametrosMetodo :",
};

//#line 253 "exemploSem.y"

  private Yylex lexer;

  private String tipoClasse;
  private Object currType;
  private String currEscopo;
  private ClasseID currClass;
  private TS_entry currRetorno;
  private TabSimb ts;
  private int nroAtributos;
  private String atribs;

  public static TS_entry Tp_INT =  new TS_entry("int", null, "", ClasseID.TipoBase);
	public static TS_entry Tp_DOUBLE =  new TS_entry("double", null, "", ClasseID.TipoBase);
  public static TS_entry Tp_BOOL = new TS_entry("bool", null, "", ClasseID.TipoBase);
  public static TS_entry Tp_STRING = new TS_entry("string", null, "", ClasseID.TipoBase);
	public static TS_entry Tp_CLASS = new TS_entry("tipo de classe", null, "", ClasseID.TipoClasse);
  public static TS_entry Tp_ERRO = new TS_entry("_erro_", null, "", ClasseID.TipoBase);

  public static final int ARRAY = 1500;
  public static final int ATRIB = 1600;


  private int yylex () {
    int yyl_return = -1;
    try {
      yylval = new ParserVal(0);
      yyl_return = lexer.yylex();
    }
    catch (IOException e) {
      System.err.println("IO error :"+e);
    }
    return yyl_return;
  }


  public void yyerror (String error) {
    System.err.println ("Erro (linha: "+ lexer.getLine() + ")\tMensagem: "+error);
  }


  public Parser(Reader r) {
    lexer = new Yylex(r, this);

    ts = new TabSimb();

    //
    // não me parece que necessitem estar na TS
    // já que criei todas como public static...
    //
    ts.insert(Tp_ERRO);
    ts.insert(Tp_INT);
    ts.insert(Tp_DOUBLE);
    ts.insert(Tp_BOOL);
    ts.insert(Tp_STRING);
		ts.insert(Tp_CLASS);


  }

  public void setDebug(boolean debug) {
    yydebug = debug;
  }

  public void listarTS() { ts.listar();}

  public static void main(String args[]) throws IOException {
    System.out.println("\n\nVerificador semantico simples\n");


    Parser yyparser;
    if ( args.length > 0 ) {
      // parse a file
      yyparser = new Parser(new FileReader(args[0]));
    }
    else {
      // interactive mode
      System.out.println("[Quit with CTRL-D]");
      System.out.print("Programa de entrada:\n");
	    yyparser = new Parser(new InputStreamReader(System.in));
    }

    yyparser.yyparse();

  	yyparser.listarTS();

	  System.out.print("\n\nFeito!\n");

  }


   TS_entry validaTipo(int operador, TS_entry A, TS_entry B) {

         switch ( operador ) {
              case ATRIB:
                    if ( (A == Tp_INT && B == Tp_INT)                        ||
                         ((A == Tp_DOUBLE && (B == Tp_INT || B == Tp_DOUBLE))) ||
                         (A ==Tp_STRING)                                     ||
                         (A == B) )
                         return A;
                     else
                         yyerror("(sem) tipos incomp. para atribuicao: "+ A.getTipoStr() + " = "+B.getTipoStr());
                    break;

            case '-' :
                          if ( A == Tp_INT && B == Tp_INT)
                                return Tp_INT;
                          else if ( (A == Tp_DOUBLE && (B == Tp_INT || B == Tp_DOUBLE)) ||
      				              		      (B == Tp_DOUBLE && (A == Tp_INT || A == Tp_DOUBLE)) )
                               return Tp_DOUBLE;
                          else
                              yyerror("(sem) tipos incomp. para subtracao: "+ A.getTipoStr() + " + "+B.getTipoStr());
                          break;
              case '+' :
                    if ( A == Tp_INT && B == Tp_INT)
                          return Tp_INT;
                    else if ( (A == Tp_DOUBLE && (B == Tp_INT || B == Tp_DOUBLE)) ||
				              		      (B == Tp_DOUBLE && (A == Tp_INT || A == Tp_DOUBLE)) )
                         return Tp_DOUBLE;
                    else if (A==Tp_STRING || B==Tp_STRING)
                        return Tp_STRING;
                    else
                        yyerror("(sem) tipos incomp. para soma: "+ A.getTipoStr() + " + "+B.getTipoStr());
                    break;

            case '/' :
                      if ( A == Tp_INT && B == Tp_INT)
                                  return Tp_INT;
                      else if ( (A == Tp_DOUBLE && (B == Tp_INT || B == Tp_DOUBLE)) ||
              				            (B == Tp_DOUBLE && (A == Tp_INT || A == Tp_DOUBLE)) )
                                  return Tp_DOUBLE;
                      else
                                  yyerror("(sem) tipos incomp. para divisao: "+ A.getTipoStr() + " + "+B.getTipoStr());
                                  break;

            case '*' :
                      if ( A == Tp_INT && B == Tp_INT)
                                   return Tp_INT;
                      else if ( (A == Tp_DOUBLE && (B == Tp_INT || B == Tp_DOUBLE)) ||
                                (B == Tp_DOUBLE && (A == Tp_INT || A == Tp_DOUBLE)) )
                                   return Tp_DOUBLE;
                      else
                                   yyerror("(sem) tipos incomp. para multiplicacao: "+ A.getTipoStr() + " + "+B.getTipoStr());
                                   break;


             case '>' :
   	              if ((A == Tp_INT || A == Tp_DOUBLE) && (B == Tp_INT || B == Tp_DOUBLE))
                         return Tp_BOOL;
					        else
                         yyerror("(sem) tipos incomp. para op relacional: "+ A.getTipoStr() + " > "+B.getTipoStr());
			            break;
            case '<' :
        	        if ((A == Tp_INT || A == Tp_DOUBLE) && (B == Tp_INT || B == Tp_DOUBLE))
                        return Tp_BOOL;
     					    else
                        yyerror("(sem) tipos incomp. para op relacional: "+ A.getTipoStr() + " > "+B.getTipoStr());
     			        break;

           case GREATEREQUAL :
              	  if ((A == Tp_INT || A == Tp_DOUBLE) && (B == Tp_INT || B == Tp_DOUBLE))
                        return Tp_BOOL;
           				else
                        yyerror("(sem) tipos incomp. para op relacional: "+ A.getTipoStr() + " > "+B.getTipoStr());
           			  break;

           case LESSEREQUAL :
                 if ((A == Tp_INT || A == Tp_DOUBLE) && (B == Tp_INT || B == Tp_DOUBLE))
                        return Tp_BOOL;
                 else
                        yyerror("(sem) tipos incomp. para op relacional: "+ A.getTipoStr() + " > "+B.getTipoStr());
                 break;

             case AND:
 	                if (A == Tp_BOOL && B == Tp_BOOL)
                         return Tp_BOOL;
					       else
                        yyerror("(sem) tipos incomp. para op lógica: "+ A.getTipoStr() + " && "+B.getTipoStr());
                 break;

             case OR:
                  if (A == Tp_BOOL && B == Tp_BOOL)
                         return Tp_BOOL;
                  else
                        yyerror("(sem) tipos incomp. para op lógica: "+ A.getTipoStr() + " && "+B.getTipoStr());
                  break;
            						}
            return Tp_ERRO;
				}

/*
  | IDENT '=' exp
                 { TS_entry nodo = ts.pesquisa($1);
    	             if (nodo == null)
                       yyerror("(sem) variavel >" + $1 + "< nao declarada");
                   else
                       $$ = validaTipo(ATRIB, nodo.getTipo(), (TS_entry)$3);
                   }
*/
//#line 745 "Parser.java"
//###############################################################
// method: yylexdebug : check lexer state
//###############################################################
void yylexdebug(int state,int ch)
{
String s=null;
  if (ch < 0) ch=0;
  if (ch <= YYMAXTOKEN) //check index bounds
     s = yyname[ch];    //now get it
  if (s==null)
    s = "illegal-symbol";
  debug("state "+state+", reading "+ch+" ("+s+")");
}





//The following are now global, to aid in error reporting
int yyn;       //next next thing to do
int yym;       //
int yystate;   //current parsing state from state table
String yys;    //current token string


//###############################################################
// method: yyparse : parse input and execute indicated items
//###############################################################
int yyparse()
{
boolean doaction;
  init_stacks();
  yynerrs = 0;
  yyerrflag = 0;
  yychar = -1;          //impossible char forces a read
  yystate=0;            //initial state
  state_push(yystate);  //save it
  val_push(yylval);     //save empty value
  while (true) //until parsing is done, either correctly, or w/error
    {
    doaction=true;
    if (yydebug) debug("loop"); 
    //#### NEXT ACTION (from reduction table)
    for (yyn=yydefred[yystate];yyn==0;yyn=yydefred[yystate])
      {
      if (yydebug) debug("yyn:"+yyn+"  state:"+yystate+"  yychar:"+yychar);
      if (yychar < 0)      //we want a char?
        {
        yychar = yylex();  //get next token
        if (yydebug) debug(" next yychar:"+yychar);
        //#### ERROR CHECK ####
        if (yychar < 0)    //it it didn't work/error
          {
          yychar = 0;      //change it to default string (no -1!)
          if (yydebug)
            yylexdebug(yystate,yychar);
          }
        }//yychar<0
      yyn = yysindex[yystate];  //get amount to shift by (shift index)
      if ((yyn != 0) && (yyn += yychar) >= 0 &&
          yyn <= YYTABLESIZE && yycheck[yyn] == yychar)
        {
        if (yydebug)
          debug("state "+yystate+", shifting to state "+yytable[yyn]);
        //#### NEXT STATE ####
        yystate = yytable[yyn];//we are in a new state
        state_push(yystate);   //save it
        val_push(yylval);      //push our lval as the input for next rule
        yychar = -1;           //since we have 'eaten' a token, say we need another
        if (yyerrflag > 0)     //have we recovered an error?
           --yyerrflag;        //give ourselves credit
        doaction=false;        //but don't process yet
        break;   //quit the yyn=0 loop
        }

    yyn = yyrindex[yystate];  //reduce
    if ((yyn !=0 ) && (yyn += yychar) >= 0 &&
            yyn <= YYTABLESIZE && yycheck[yyn] == yychar)
      {   //we reduced!
      if (yydebug) debug("reduce");
      yyn = yytable[yyn];
      doaction=true; //get ready to execute
      break;         //drop down to actions
      }
    else //ERROR RECOVERY
      {
      if (yyerrflag==0)
        {
        yyerror("syntax error");
        yynerrs++;
        }
      if (yyerrflag < 3) //low error count?
        {
        yyerrflag = 3;
        while (true)   //do until break
          {
          if (stateptr<0)   //check for under & overflow here
            {
            yyerror("stack underflow. aborting...");  //note lower case 's'
            return 1;
            }
          yyn = yysindex[state_peek(0)];
          if ((yyn != 0) && (yyn += YYERRCODE) >= 0 &&
                    yyn <= YYTABLESIZE && yycheck[yyn] == YYERRCODE)
            {
            if (yydebug)
              debug("state "+state_peek(0)+", error recovery shifting to state "+yytable[yyn]+" ");
            yystate = yytable[yyn];
            state_push(yystate);
            val_push(yylval);
            doaction=false;
            break;
            }
          else
            {
            if (yydebug)
              debug("error recovery discarding state "+state_peek(0)+" ");
            if (stateptr<0)   //check for under & overflow here
              {
              yyerror("Stack underflow. aborting...");  //capital 'S'
              return 1;
              }
            state_pop();
            val_pop();
            }
          }
        }
      else            //discard this token
        {
        if (yychar == 0)
          return 1; //yyabort
        if (yydebug)
          {
          yys = null;
          if (yychar <= YYMAXTOKEN) yys = yyname[yychar];
          if (yys == null) yys = "illegal-symbol";
          debug("state "+yystate+", error recovery discards token "+yychar+" ("+yys+")");
          }
        yychar = -1;  //read another
        }
      }//end error recovery
    }//yyn=0 loop
    if (!doaction)   //any reason not to proceed?
      continue;      //skip action
    yym = yylen[yyn];          //get count of terminals on rhs
    if (yydebug)
      debug("state "+yystate+", reducing "+yym+" by rule "+yyn+" ("+yyrule[yyn]+")");
    if (yym>0)                 //if count of rhs not 'nil'
      yyval = val_peek(yym-1); //get current semantic value
    yyval = dup_yyval(yyval); //duplicate yyval if ParserVal is used as semantic value
    switch(yyn)
      {
//########## USER-SUPPLIED ACTIONS ##########
case 1:
//#line 27 "exemploSem.y"
{ currEscopo = "Global"; currClass = ClasseID.VarGlobal; }
break;
case 3:
//#line 29 "exemploSem.y"
{tipoClasse = (String)val_peek(0).sval;  TS_entry nodo = ts.pesquisa(val_peek(0).sval);
    	                    if (nodo != null && nodo.getEscopo().equals(currEscopo))
                              yyerror("(sem) variavel >" + val_peek(0).sval + "< jah declarada");

                          else ts.insert(new TS_entry(val_peek(0).sval, (TS_entry)Tp_CLASS, currEscopo, ClasseID.TipoClasse));
                        }
break;
case 7:
//#line 45 "exemploSem.y"
{currType = val_peek(0).obj;}
break;
case 10:
//#line 49 "exemploSem.y"
{  TS_entry nodo = ts.pesquisa(val_peek(2).sval);
    	                    if (nodo != null && nodo.getEscopo().equals(currEscopo))
                              yyerror("(sem) variavel >" + val_peek(2).sval + "< jah declarada");

                          else ts.insert(new TS_entry(val_peek(2).sval, (TS_entry)currType, currEscopo, currClass));
                        }
break;
case 11:
//#line 57 "exemploSem.y"
{  TS_entry nodo = ts.pesquisa(val_peek(1).sval);
    	                    if (nodo != null && nodo.getEscopo().equals(currEscopo))
                              yyerror("(sem) variavel >" + val_peek(1).sval + "< jah declarada");

                          else ts.insert(new TS_entry(val_peek(1).sval, (TS_entry)currType, currEscopo, currClass));
                        }
break;
case 13:
//#line 66 "exemploSem.y"
{ yyval.obj = Tp_INT; }
break;
case 14:
//#line 67 "exemploSem.y"
{ yyval.obj = Tp_BOOL; }
break;
case 15:
//#line 68 "exemploSem.y"
{ yyval.obj = Tp_DOUBLE; }
break;
case 16:
//#line 69 "exemploSem.y"
{ yyval.obj = Tp_STRING; }
break;
case 17:
//#line 70 "exemploSem.y"
{ TS_entry nodo = ts.pesquisa(val_peek(0).sval);
		 									if(val_peek(0).sval.equals(tipoClasse)){
											    yyval.obj = Tp_CLASS;
											}else{
                        yyval.obj = Tp_ERRO;
												yyerror("(sem) Nome de tipo <" + val_peek(0).sval + "> nao declarado ");
											}
              }
break;
case 18:
//#line 81 "exemploSem.y"
{currClass = ClasseID.VarLocal;}
break;
case 20:
//#line 85 "exemploSem.y"
{nroAtributos = 0;}
break;
case 22:
//#line 89 "exemploSem.y"
{ currEscopo = (String)val_peek(0).sval;}
break;
case 23:
//#line 89 "exemploSem.y"
{currRetorno = Tp_INT;
TS_entry nodo = ts.pesquisaMetodo(val_peek(4).sval,nroAtributos,atribs);
                         if (nodo != null && nodo.getEscopo().equals(currEscopo))
                             yyerror("metodo ja declarado >" + val_peek(4).sval + "< jah declarada");

                         else ts.insert(new TS_entry(val_peek(4).sval, Tp_INT, nroAtributos, currEscopo, ClasseID.NomeFuncao,atribs));}
break;
case 25:
//#line 95 "exemploSem.y"
{ currEscopo = (String)val_peek(0).sval;}
break;
case 26:
//#line 95 "exemploSem.y"
{currRetorno = Tp_BOOL ;
   TS_entry nodo = ts.pesquisaMetodo(val_peek(4).sval,nroAtributos,atribs);
                            if (nodo != null && nodo.getEscopo().equals(currEscopo))
                                yyerror("metodo ja declarado >" + val_peek(4).sval + "< jah declarada");

                            else ts.insert(new TS_entry(val_peek(4).sval, Tp_BOOL,nroAtributos, currEscopo, ClasseID.NomeFuncao,atribs));}
break;
case 28:
//#line 101 "exemploSem.y"
{ currEscopo = (String)val_peek(0).sval;}
break;
case 29:
//#line 101 "exemploSem.y"
{ currRetorno = Tp_DOUBLE ;
   TS_entry nodo = ts.pesquisaMetodo(val_peek(4).sval,nroAtributos,atribs);
                            if (nodo != null && nodo.getEscopo().equals(currEscopo))
                                yyerror("metodo ja declarado >" + val_peek(4).sval + "< jah declarada");

                            else ts.insert(new TS_entry(val_peek(4).sval, Tp_DOUBLE,nroAtributos, currEscopo, ClasseID.NomeFuncao,atribs));}
break;
case 31:
//#line 107 "exemploSem.y"
{ currEscopo = (String)val_peek(0).sval;}
break;
case 32:
//#line 107 "exemploSem.y"
{ currRetorno = Tp_STRING ;
   TS_entry nodo = ts.pesquisaMetodo(val_peek(4).sval,nroAtributos,atribs);
                            if (nodo != null && nodo.getEscopo().equals(currEscopo))
                                yyerror("metodo ja declarado >" + val_peek(4).sval + "< jah declarada");

                            else ts.insert(new TS_entry(val_peek(4).sval, Tp_STRING,nroAtributos, currEscopo, ClasseID.NomeFuncao,atribs));}
break;
case 34:
//#line 113 "exemploSem.y"
{         if(!(val_peek(4).sval.equals(tipoClasse))){
											  yyerror("(sem) Nome de tipo <" + val_peek(4).sval + "> nao declarado ");
											}
              }
break;
case 35:
//#line 119 "exemploSem.y"
{nroAtributos++;atribs = "";}
break;
case 36:
//#line 119 "exemploSem.y"
{ TS_entry nodo = ts.pesquisa(val_peek(1).sval);
    	                    		if (nodo != null && nodo.getEscopo().equals(currEscopo))
                              		yyerror("(sem) variavel >" + val_peek(1).sval + "< jah declarada");
                          		else{ ts.insert(new TS_entry(val_peek(1).sval, (TS_entry)val_peek(2).obj, currEscopo, currClass));
                              atribs+=((TS_entry)val_peek(2).obj).getTipoStr();}
                        }
break;
case 38:
//#line 128 "exemploSem.y"
{nroAtributos++;}
break;
case 39:
//#line 128 "exemploSem.y"
{  TS_entry nodo = ts.pesquisa(val_peek(1).sval);
    	                    		if (nodo != null && nodo.getEscopo().equals(currEscopo))
                              		yyerror("(sem) variavel >" + val_peek(1).sval + "< jah declarada");
                          		else {ts.insert(new TS_entry(val_peek(1).sval, (TS_entry)val_peek(2).obj, currEscopo, currClass));
                              atribs=atribs+" "+((TS_entry)val_peek(2).obj).getTipoStr();};
                        }
break;
case 45:
//#line 146 "exemploSem.y"
{ if (val_peek(0).obj != currRetorno)
  yyerror("Tipo de retorno incorreto"); }
break;
case 53:
//#line 160 "exemploSem.y"
{if (val_peek(0).obj != Tp_BOOL){
          yyerror("Expressao deve ser booleana");}
          }
break;
case 61:
//#line 174 "exemploSem.y"
{if (val_peek(0).obj != Tp_STRING ){
      yyerror("Expressao deve ser do tipo string");}
      }
break;
case 63:
//#line 179 "exemploSem.y"
{if (val_peek(0).obj != Tp_BOOL && val_peek(0).obj != Tp_INT && val_peek(0).obj != Tp_DOUBLE && val_peek(0).obj != Tp_STRING ){
      yyerror("Expressao deve ser de um dos tipos base");}
      }
break;
case 64:
//#line 184 "exemploSem.y"
{if (val_peek(0).obj != Tp_BOOL && val_peek(0).obj != Tp_INT && val_peek(0).obj != Tp_DOUBLE && val_peek(0).obj != Tp_STRING ){
      yyerror("Expressao deve ser de um dos tipos base");}
      }
break;
case 66:
//#line 190 "exemploSem.y"
{if (val_peek(0).obj != Tp_BOOL){
      yyerror("Expressao deve ser booleana");}
      }
break;
case 68:
//#line 196 "exemploSem.y"
{ TS_entry nodo = ts.pesquisa(val_peek(2).sval);
                         if (nodo == null)
                            yyerror("(sem) var <" + val_peek(2).sval + "> nao declarada");
                            else{
                              validaTipo(ATRIB, (TS_entry)nodo.getTipo(), (TS_entry)val_peek(0).obj);
                            }
      }
break;
case 69:
//#line 202 "exemploSem.y"
{if (val_peek(0).obj != Tp_BOOL){
                          yyerror("Expressao deve ser booleana");}
                          }
break;
case 71:
//#line 209 "exemploSem.y"
{ yyval.obj = validaTipo('+', (TS_entry)val_peek(2).obj, (TS_entry)val_peek(0).obj); }
break;
case 72:
//#line 210 "exemploSem.y"
{ yyval.obj = validaTipo('*', (TS_entry)val_peek(2).obj, (TS_entry)val_peek(0).obj); }
break;
case 73:
//#line 211 "exemploSem.y"
{ yyval.obj = validaTipo('-', (TS_entry)val_peek(2).obj, (TS_entry)val_peek(0).obj); }
break;
case 74:
//#line 212 "exemploSem.y"
{ yyval.obj = validaTipo('/', (TS_entry)val_peek(2).obj, (TS_entry)val_peek(0).obj); }
break;
case 75:
//#line 213 "exemploSem.y"
{ yyval.obj = validaTipo('>', (TS_entry)val_peek(2).obj, (TS_entry)val_peek(0).obj);}
break;
case 76:
//#line 214 "exemploSem.y"
{ yyval.obj = validaTipo('<', (TS_entry)val_peek(2).obj, (TS_entry)val_peek(0).obj);}
break;
case 77:
//#line 215 "exemploSem.y"
{ yyval.obj = validaTipo(AND, (TS_entry)val_peek(2).obj, (TS_entry)val_peek(0).obj); }
break;
case 78:
//#line 216 "exemploSem.y"
{ yyval.obj = validaTipo(OR, (TS_entry)val_peek(2).obj, (TS_entry)val_peek(0).obj); }
break;
case 79:
//#line 217 "exemploSem.y"
{ yyval.obj = validaTipo(LESSEREQUAL, (TS_entry)val_peek(2).obj, (TS_entry)val_peek(0).obj); }
break;
case 80:
//#line 218 "exemploSem.y"
{ yyval.obj = validaTipo(GREATEREQUAL, (TS_entry)val_peek(2).obj, (TS_entry)val_peek(0).obj); }
break;
case 81:
//#line 219 "exemploSem.y"
{yyval.obj = Tp_BOOL;}
break;
case 82:
//#line 220 "exemploSem.y"
{yyval.obj = Tp_BOOL;}
break;
case 83:
//#line 221 "exemploSem.y"
{ yyval.obj = Tp_INT;}
break;
case 84:
//#line 222 "exemploSem.y"
{yyval.obj = Tp_DOUBLE;}
break;
case 85:
//#line 223 "exemploSem.y"
{ yyval.obj = val_peek(1).obj; }
break;
case 86:
//#line 224 "exemploSem.y"
{ yyval.obj = Tp_STRING; }
break;
case 87:
//#line 225 "exemploSem.y"
{ TS_entry nodo = ts.pesquisa(val_peek(0).sval);
    	                 if (nodo == null)
	                        yyerror("(sem) var <" + val_peek(0).sval + "> nao declarada");
                      else{
                        if(!(nodo.getEscopo().equals(currEscopo))&& !(nodo.getEscopo().equals("Global"))){
                          yyerror("(sem) var <" + val_peek(0).sval + "> nao declarada");
                          yyval.obj = Tp_ERRO;
                          }
                          else
			                    yyval.obj = nodo.getTipo();
                        }
			            }
break;
case 88:
//#line 237 "exemploSem.y"
{yyval.obj = validaTipo(ATRIB, (TS_entry)val_peek(2).obj, (TS_entry)val_peek(0).obj);}
break;
//#line 1183 "Parser.java"
//########## END OF USER-SUPPLIED ACTIONS ##########
    }//switch
    //#### Now let's reduce... ####
    if (yydebug) debug("reduce");
    state_drop(yym);             //we just reduced yylen states
    yystate = state_peek(0);     //get new state
    val_drop(yym);               //corresponding value drop
    yym = yylhs[yyn];            //select next TERMINAL(on lhs)
    if (yystate == 0 && yym == 0)//done? 'rest' state and at first TERMINAL
      {
      if (yydebug) debug("After reduction, shifting from state 0 to state "+YYFINAL+"");
      yystate = YYFINAL;         //explicitly say we're done
      state_push(YYFINAL);       //and save it
      val_push(yyval);           //also save the semantic value of parsing
      if (yychar < 0)            //we want another character?
        {
        yychar = yylex();        //get next character
        if (yychar<0) yychar=0;  //clean, if necessary
        if (yydebug)
          yylexdebug(yystate,yychar);
        }
      if (yychar == 0)          //Good exit (if lex returns 0 ;-)
         break;                 //quit the loop--all DONE
      }//if yystate
    else                        //else not done yet
      {                         //get next state and push, for next yydefred[]
      yyn = yygindex[yym];      //find out where to go
      if ((yyn != 0) && (yyn += yystate) >= 0 &&
            yyn <= YYTABLESIZE && yycheck[yyn] == yystate)
        yystate = yytable[yyn]; //get new state
      else
        yystate = yydgoto[yym]; //else go to new defred
      if (yydebug) debug("after reduction, shifting from state "+state_peek(0)+" to state "+yystate+"");
      state_push(yystate);     //going again, so push state & val...
      val_push(yyval);         //for next action
      }
    }//main loop
  return 0;//yyaccept!!
}
//## end of method parse() ######################################



//## run() --- for Thread #######################################
/**
 * A default run method, used for operating this parser
 * object in the background.  It is intended for extending Thread
 * or implementing Runnable.  Turn off with -Jnorun .
 */
public void run()
{
  yyparse();
}
//## end of method run() ########################################



//## Constructors ###############################################
/**
 * Default constructor.  Turn off with -Jnoconstruct .

 */
public Parser()
{
  //nothing to do
}


/**
 * Create a parser, setting the debug to true or false.
 * @param debugMe true for debugging, false for no debug.
 */
public Parser(boolean debugMe)
{
  yydebug=debugMe;
}
//###############################################################



}
//################### END OF CLASS ##############################
