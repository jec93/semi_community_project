function closeModal(obj) {
  const modalBg = $(obj).parents(".modal-bg");
  modalBg.removeClass("open");
}
function openModal(modal) {
  console.log(modal);
  $(modal).addClass("open");
}
