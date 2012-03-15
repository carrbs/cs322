	.align 4
A_go:
!locals=1, max_args=2
	save %sp,-96,%sp
! [MOVE (VAR 1) (CONST 0)]
	mov 0,%l0
	st %l0,[%fp-4]
! [CJUMP <= (PARAM 1) (CONST 0) (NAME L0)]
