# Makefile for MINI compiler (v1.6). (Jingke Li)
#
JFLAGS = -g
JC = javac
JCC = javacc

.SUFFIXES: .java .class

.java.class:
	$(JC) $(JFLAGS) $*.java

lexer/miniLexer.java: lexer/miniLexer.jj
	(cd lexer; $(JCC) miniLexer.jj)

parser0/miniParser0.java: parser0/miniParser0.jj
	(cd parser0; $(JCC) miniParser0.jj)

parser/miniParser.java: parser/miniParser.jj
	(cd parser; $(JCC) miniParser.jj)

astpsr/astParser.java: astpsr/astParser.jj
	(cd astpsr; $(JCC) astParser.jj)

irpsr/irParser.java: irpsr/irParser.jj
	(cd irpsr; $(JCC) irParser.jj)

ast: 	ast/ArrayElm.class ast/ArrayLen.class ast/ArrayType.class              \
	ast/Assign.class ast/Ast.class ast/BasicType.class ast/Binop.class     \
	ast/Block.class ast/BoolVal.class ast/Call.class ast/CallStmt.class    \
	ast/ClassDecl.class ast/ClassDeclList.class ast/Exp.class              \
	ast/ExpList.class ast/Field.class ast/FloatVal.class ast/Formal.class  \
	ast/FormalList.class ast/Id.class ast/If.class ast/IntVal.class        \
	ast/AstList.class ast/MethodDecl.class ast/MethodDeclList.class        \
	ast/NewArray.class ast/NewObj.class ast/ObjType.class ast/Print.class  \
	ast/Program.class ast/Relop.class ast/Return.class ast/Stmt.class      \
	ast/StmtList.class ast/StrVal.class ast/This.class ast/Type.class      \
	ast/Unop.class ast/VarDecl.class ast/VarDeclList.class ast/While.class

ir: 	ir/BINOP.class ir/CALL.class ir/CALLST.class ir/CJUMP.class 	    \
	ir/CONST.class ir/ESEQ.class ir/EXP.class ir/EXPlist.class 	    \
	ir/FIELD.class ir/FLOAT.class ir/FUNC.class ir/FUNClist.class       \
	ir/IR.class ir/JUMP.class ir/LABEL.class ir/MEM.class ir/MOVE.class \
	ir/NAME.class ir/PARAM.class ir/PROG.class ir/RETURN.class          \
	ir/STMT.class ir/STMTlist.class ir/STRING.class ir/TEMP.class       \
	ir/VAR.class ir/IrVI.class 

lexer: 	lexer/miniLexer.class lexer/TestLexer.class

parser0: parser0/miniParser0.class parser0/TestParser0.class

parser: ast parser/miniParser.class parser/TestParser.class

astpsr: ast astpsr/astParser.class astpsr/TestASTparser.class

irpsr: 	ir irpsr/irParser.class irpsr/TestIRparser.class

symbol: ast astpsr symbol/Table.class symbol/ClassRec.class \
	symbol/MethodRec.class symbol/VarRec.class          \
	symbol/SymbolVisitor.class symbol/TestSymbol.class

typechk: ast astpsr symbol typechk/TypeVisitor.class \
	typechk/TestType.class

irgen0:	ast astpsr ir irgen0/IrgenVisitor0.class irgen0/TestIrgen0.class

irgen:	ast astpsr ir symbol typechk irgen/Canon.class irgen/TestCanon.class \
	irgen/IrgenVisitor.class irgen/TestIrgen.class

irgen-opt: ast astpsr ir symbol typechk irgen/Canon.class irgen/TestCanon.class \
	irgen/IrgenVisitorOpt.class irgen/TestIrgenOpt.class

interp: ir interp/Val.class interp/Intval.class interp/Floatval.class   \
	interp/Interp.class interp/TestInterp.class

codegen: ir irpsr codegen/Imm13.class codegen/Imm32.class \
	codegen/Operand.class codegen/Reg.class codegen/RegOffset.class \
	codegen/Codegen.class codegen/TestCodegen.class

front: 	parser ast symbol typechk

clean_ast:
	$(RM) ast/*.class

clean_lexer:
	$(RM) lexer/*.class lexer/miniLexer.java

clean_parser:
	$(RM) parser/*.class parser/miniParser.java

clean_symbol:
	$(RM) symbol/*.class

clean_typechk:
	$(RM) typechk/*.class

clean_irgen:
	$(RM) irgen/*.class

clean_interp:
	$(RM) interp/*.class

clean_codegen:
	$(RM) codegen/*.class
