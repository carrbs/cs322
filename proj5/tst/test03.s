	.global main
	.align 4
main:
!locals=2, max_args=0
	save %sp,-104,%sp
! [MOVE (TEMP 1) (CONST 1)]
	mov 1,%l0
	mov 1,%l1
! [CJUMP > (CONST 1) (CONST 2) (NAME L0)]
	mov 1,%l0
	mov 2,%l2
	cmp %l0,%l2
	bg L0
	nop
! [MOVE (TEMP 1) (CONST 0)]
	mov 0,%l0
	mov 0,%l1
! [LABEL L0]
L0:
! [MOVE (TEMP 2) (CONST 1)]
	mov 1,%l0
	mov 1,%l2
! [CJUMP < (CONST 3) (CONST 4) (NAME L1)]
	mov 3,%l0
	mov 4,%l3
	cmp %l0,%l3
	bl L1
	nop
! [MOVE (TEMP 2) (CONST 0)]
	mov 0,%l0
	mov 0,%l2
! [LABEL L1]
L1:
! [MOVE (VAR 1) (BINOP || (TEMP 1) (BINOP && (TEMP 2) (CONST 1)))]
	mov 1,%l4
	and %l3,%l4,%l3
	or %l0,%l3,%l0
	st %l0,[%fp-4]
! [MOVE (VAR 2) (BINOP - (BINOP + (CONST 2) (BINOP * (CONST 2) (CONST 4))) (BINOP / (CONST 9) (CONST 3)))]
	mov 2,%l0
	mov 2,%l3
	mov 4,%l4
	smul %l3,%l4,%l3
	add %l0,%l3,%l0
	mov 9,%l3
	mov 3,%l4
	wr %g0,%g0,%y
	sdiv %l3,%l4,%l3
	sub %l0,%l3,%l0
	st %l0,[%fp-8]
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
	ret
	restore

L$1:	.asciz "%d\n"

!Total regs:  5
!Total insts: 50
