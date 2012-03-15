	.global main
	.align 4
main:
!locals=2, max_args=3
	save %sp,-104,%sp
! [MOVE (TEMP 1) (CALL (NAME malloc) ( (BINOP * (CONST 11) (NAME wSZ))))]
