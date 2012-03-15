	.align 4
body_foo:
!locals=1, max_args=0
	save %sp,-96,%sp
! [RETURN (PARAM 1)]
	ret
	restore

	ret
	restore

	.align 4
test_foo:
!locals=1, max_args=0
	save %sp,-96,%sp
! [RETURN (PARAM 1)]
	ret
	restore

	ret
	restore

	.global main
	.align 4
main:
!locals=3, max_args=2
	save %sp,-104,%sp
! [MOVE (TEMP 1) (CALL (NAME malloc) ( (NAME wSZ)))]
