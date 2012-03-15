	.align 4
A_foo:
!locals=0, max_args=0
	save %sp,-96,%sp
! [RETURN (PARAM 1)]
	ret
	restore

	ret
	restore

	.global main
	.align 4
main:
!locals=1, max_args=2
	save %sp,-96,%sp
! [MOVE (TEMP 1) (CALL (NAME malloc) ( (NAME wSZ)))]
