	.align 4
test_foo:
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
!locals=3, max_args=3
	save %sp,-104,%sp
! [MOVE (VAR 1) (CONST 1)]
	mov 1,%l0
	st %l0,[%fp-4]
! [MOVE (TEMP 1) (CALL (NAME test_foo) ( (PARAM 0) (CONST 1) (CONST 2)))]
