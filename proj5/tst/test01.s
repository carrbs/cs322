	.global main
	.align 4
main:
!locals=0, max_args=0
	save %sp,-96,%sp
! [CALLST (NAME print) ( (STRING "123"))]
	sethi %hi(L$2),%o0
	or %o0, %lo(L$2),%o0
	call printf
	nop
! [CALLST (NAME print) ( (CONST 123))]
	mov 123,%o1
	sethi %hi(L$1),%o0
	or %o0, %lo(L$1),%o0
	call printf
	nop
! [CALLST (NAME print) ()]
	sethi %hi(L$3),%o0
	or %o0, %lo(L$3),%o0
	call printf
	nop
! [CALLST (NAME print) ( (CONST 1))]
	mov 1,%o1
	sethi %hi(L$1),%o0
	or %o0, %lo(L$1),%o0
	call printf
	nop
L$1:	.asciz "%d\n"
L$2:	.asciz "123\n"
L$3:	.asciz "\n"

!Total regs:  2
!Total insts: 25
