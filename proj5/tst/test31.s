	.align 4
A_f:
!locals=0, max_args=0
	save %sp,-96,%sp
! [RETURN (BINOP - (PARAM 1) (CONST 1))]
	ret
	restore

	ret
	restore

	.align 4
A_g:
!locals=0, max_args=2
	save %sp,-96,%sp
! [MOVE (TEMP 3) (CALL (NAME A_f) ( (PARAM 0) (PARAM 1)))]
