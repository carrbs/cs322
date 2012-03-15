	.global main
	.align 4
main:
!locals=3, max_args=0
	save %sp,-104,%sp
! [MOVE (VAR 1) (CONST 2)]
	mov 2,%l0
	st %l0,[%fp-4]
! [MOVE (VAR 2) (CONST 3)]
	mov 3,%l0
	st %l0,[%fp-8]
! [MOVE (VAR 3) (BINOP + (VAR 1) (VAR 2))]
	ld [%fp-4],%l0
	ld [%fp-8],%l1
	add %l0,%l1,%l0
	st %l0,[%fp-12]
! [CALLST (NAME print) ( (VAR 3))]
	ld [%fp-12],%o1
	sethi %hi(L$1),%o0
	or %o0, %lo(L$1),%o0
	call printf
	nop
! [CJUMP != (VAR 3) (CONST 5) (NAME L0)]
	ld [%fp-12],%l0
	mov 5,%l1
	cmp %l0,%l1
	bne L0
	nop
! [CALLST (NAME print) ( (STRING "OK"))]
	sethi %hi(L$2),%o0
	or %o0, %lo(L$2),%o0
	call printf
	nop
! [LABEL L0]
L0:
	ret
	restore

L$1:	.asciz "%d\n"
L$2:	.asciz "OK\n"

!Total regs:  2
!Total insts: 30
