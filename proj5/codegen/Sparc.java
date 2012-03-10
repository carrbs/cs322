// SPARC codegen for MINI v1.6 (Jingke Li)
//
// Sparc.java --- A Sparc library consisting of
//                  - register definitions
//                  - register-allocation routines
//                  - instruction-emission routines
//                  - and some other misc. routines
//
// All routines are defined as stitic routines, so use them with
//   a 'Sparc.' prefix.
//

package codegen;

public class Sparc {
  static private final int MAXREGS=22;    // number of registers
  static private final int MINARGSIZE=24; // minumum arg-building area size
  static final int ARGOFFSET=68;          // offset of 1st arg in stack frame
  static final int WORDSIZE=4;            // word size

  static private int instCnt = 0;         // track inst count
  static private int regUseCnt = 0; 	  // track reg-use
  static private int maxRegCnt = 0; 	  // max reg-use count

  // special registers
  static final Reg 
      regSP = new Reg("%sp", Reg.RSRV), // stack ptr
      regFP = new Reg("%fp", Reg.RSRV), // frame ptr
      regG0 = new Reg("%g0", Reg.RSRV), // always zero
      regI7 = new Reg("%i7", Reg.RSRV), // return address
      regG5 = new Reg("%g5", Reg.RSRV), // system use only
      regG6 = new Reg("%g6", Reg.RSRV), // system use only
      regG7 = new Reg("%g7", Reg.RSRV), // system use only
      regY  = new Reg("%y",  Reg.RSRV), // for div op
      regO0 = new Reg("%o0"),  // first arg
      regO1 = new Reg("%o1"),  // second arg
      regI0 = new Reg("%i0");  // return value

  // general registers
  static private final Reg[] reg = {	
      new Reg("%l0"), new Reg("%l1"), new Reg("%l2"), new Reg("%l3"),    
      new Reg("%l4"), new Reg("%l5"), new Reg("%l6"), new Reg("%l7"), 
      new Reg("%i1"), new Reg("%i2"), new Reg("%i3"), new Reg("%i4"),
      new Reg("%i5"), new Reg("%g1"), new Reg("%g2"), new Reg("%g3"), 
      new Reg("%g4"), new Reg("%o2"), new Reg("%o3"), new Reg("%o4"),
      new Reg("%o5"), new Reg("%o7") };


  // ---------------
  // Misc. routines.
  // ---------------

  static private int roundup(int x, int p) { return ((x+p-1)/p) * p; }

  static int frameSize(int varCnt, int argCnt) {
    int localSize = varCnt * WORDSIZE;
    int argSize = argCnt * WORDSIZE;
    if (argSize < MINARGSIZE) argSize = MINARGSIZE;
    return roundup(ARGOFFSET + localSize + argSize, 8);
  }

  // print reg and inst counts
  static void printStats() {
    emit("\n!Total regs:  " + maxRegCnt);
    emit("\n!Total insts: " + instCnt + "\n");
  }

  // -----------------------
  // Reg allocation routines
  // -----------------------

  // request an (arbitrary) free reg
  static Reg getReg() throws Exception { 
    for (int i=0; i<MAXREGS; i++) {
      if (reg[i].status == Reg.FREE) {
	reg[i].status = Reg.USE;
	if (++regUseCnt > maxRegCnt) 
	  maxRegCnt = regUseCnt;
	return reg[i];
      }
    }
    throw new CodegenException("Out of registers");
  }
      
  // request a specific reg
  static void getReg(Reg r) throws Exception { 
    if (r.status == Reg.FREE) {
      r.status = Reg.USE;
      if (++regUseCnt > maxRegCnt) 
	maxRegCnt = regUseCnt;
    } else {
      throw new CodegenException("Trying to use an occupied reg: " 
				 + r.name + ", status: " + r.status);
    }
  }
      
  // free a used reg, skip if it holds a temp
  static void freeReg(Reg r) {
    if (r.status == Reg.USE) {
      r.status = Reg.FREE;
      regUseCnt--;
    }
  }

  // free all used regs, including those holding temps
  static void freeAllRegs() {
    for (int i=0; i<MAXREGS; i++) {
      if ((reg[i].status == Reg.USE) ||
	  (reg[i].status == Reg.TEMP)) {
	reg[i].status = Reg.FREE;
      }
      regUseCnt = 0;
    }
  }

  // -----------------------
  // Code emission routines.
  // -----------------------

  private static void emit(String s) { System.out.print(s); }

  static void emit0(String op) { 
    emit("\t" + op + "\n"); 
    instCnt++;
  }

  static void emit2(String op, Operand x, Operand y) {
    emit("\t" + op + " "); 
    x.emit(); emit(",");
    y.emit(); emit("\n");
    instCnt++;
  }

  static void emit3(String op, Operand x, Operand y, Operand z) {
    emit("\t" + op + " ");
    x.emit(); emit(",");
    y.emit(); emit(",");
    z.emit(); emit("\n");
    instCnt++;
  }

  static void emitLoad(Operand addr, Reg reg) {
    emit("\tld [");
    addr.emit(); emit("],");
    reg.emit(); emit("\n");
    instCnt++;
  }

  static void emitStore(Reg reg, Operand addr) {
    emit("\tst "); 
    reg.emit(); emit(",["); 
    addr.emit(); emit("]\n");
    instCnt++;
  }

  // emit a string table entry
  static void emitString(String s) { 
    emit(s + "\n"); 
    instCnt++;
  }

  // emit a non-intruction line, e.g. label, comment, or empty line
  // (Instructions emitted from this routine are not included in instCnt.)
  static void emitNonInst(String s) {
    emit(s);
  }
}
