	.align 4
body_foo:
!locals=0, max_args=0
	save %sp,-96,%sp
! [RETURN (BINOP + (PARAM 1) (PARAM 2))]
	ret
	restore

	ret
	restore

	.global main
	.align 4
main:
!locals=2, max_args=3
	save %sp,-104,%sp
! [MOVE (TEMP 1) (CALL (NAME malloc) ( (NAME wSZ)))]
