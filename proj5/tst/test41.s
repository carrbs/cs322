	.global main
	.align 4
main:
!locals=1, max_args=0
	save %sp,-96,%sp
! [MOVE (VAR 1) (BINOP + (FLOAT 1.2) (BINOP * (FLOAT 2.5) (CONST 4)))]
