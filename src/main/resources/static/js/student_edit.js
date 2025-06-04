function handleEditButtonClick(btn) {
	document.getElementById("editId").value = btn.dataset.id;
	document.getElementById("editNums").value = btn.dataset.nums;
	document.getElementById("editName").value = btn.dataset.name;
	document.getElementById("editAge").value = btn.dataset.age;
	document.getElementById("editGrade").value = btn.dataset.grade;
	document.getElementById("editMajor").value = btn.dataset.major;
	document.getElementById("editScore").value = btn.dataset.score;
	const modal = new bootstrap.Modal(
		document.getElementById("editStudentModal")
	);
	modal.show();
}
