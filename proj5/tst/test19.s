	.global main
	.align 4
main:
!locals=1, max_args=1
	save %sp,-96,%sp
! [MOVE (TEMP 1) (CALL (NAME malloc) ( (BINOP * (CONST 2) (NAME wSZ))))]
