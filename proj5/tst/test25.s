	.global main
	.align 4
main:
!locals=2, max_args=0
	save %sp,-104,%sp
! [MOVE (TEMP 1) (CALL (NAME malloc) ( (NAME wSZ)))]
