	.global main
	.align 4
main:
!locals=3, max_args=0
	save %sp,-104,%sp
! [MOVE (TEMP 1) (CALL (NAME malloc) ( (BINOP * (CONST 3) (NAME wSZ))))]
