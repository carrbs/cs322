	.global main
	.align 4
main:
!locals=1, max_args=0
	save %sp,-96,%sp
! [MOVE (VAR 1) (CONST 0)]
	mov 0,%l0
	st %l0,[%fp-4]
! [CJUMP <= (CONST 2) (CONST 1) (NAME L0)]
	mov 2,%l0
	mov 1,%l1
	cmp %l0,%l1
	ble L0
	nop
! [CJUMP <= (CONST 1) (CONST 0) (NAME L1)]
	mov 1,%l0
	mov 0,%l1
	cmp %l0,%l1
	ble L1
	nop
! [MOVE (VAR 1) (CONST 1)]
	mov 1,%l0
	st %l0,[%fp-4]
! [JUMP (NAME L2)]
	ba L2
	nop
! [LABEL L1]
L1:
! [MOVE (VAR 1) (CONST 2)]
	mov 2,%l0
	st %l0,[%fp-4]
! [LABEL L2]
L2:
! [LABEL L0]
L0:
! [CALLST (NAME print) ( (VAR 1))]
	ld [%fp-4],%o1
	sethi %hi(L$1),%o0
	or %o0, %lo(L$1),%o0
	call printf
	nop
	ret
	restore

L$1:	.asciz "%d\n"

!Total regs:  2
!Total insts: 30
