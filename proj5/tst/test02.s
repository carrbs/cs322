	.global main
	.align 4
main:
!locals=3, max_args=0
	save %sp,-104,%sp
! [MOVE (VAR 1) (CONST 1)]
	mov 1,%l0
	st %l0,[%fp-4]
! [MOVE (VAR 2) (BINOP + (CONST 1) (CONST 1))]
	mov 1,%l0
	mov 1,%l1
	wr %g0,%g0,%y
	add %l0,%l1,%l2
	st %l0,[%fp-8]
! [MOVE (VAR 3) (BINOP * (CONST 3) (VAR 2))]
	mov 3,%l0
	ld [%fp-8],%l1
	wr %g0,%g0,%y
	smul %l0,%l1,%l2
	st %l0,[%fp-12]
! [CALLST (NAME print) ( (VAR 1))]
	ld [%fp-4],%o1
	sethi %hi(L$1),%o0
	or %o0, %lo(L$1),%o0
	call printf
	nop
! [CALLST (NAME print) ( (VAR 2))]
	ld [%fp-8],%o1
	sethi %hi(L$1),%o0
	or %o0, %lo(L$1),%o0
	call printf
	nop
! [CALLST (NAME print) ( (VAR 3))]
	ld [%fp-12],%o1
	sethi %hi(L$1),%o0
	or %o0, %lo(L$1),%o0
	call printf
	nop
	ret
	restore

L$1:	.asciz "%d\n"

!Total regs:  3
!Total insts: 34
