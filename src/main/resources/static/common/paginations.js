document.addEventListener("DOMContentLoaded", function () {
  const pageLinkButtons = document.querySelectorAll('.page-link');
  pageLinkButtons.forEach(function (button) {
    button.onclick = function (event) {
      const page = this.getAttribute('data-page')
      const formId = this.getAttribute('data-form-id')
      pagingSubmitForm(event, page, formId);
    }
  });
});

function pagingSubmitForm(event, page, formId) {
  event.preventDefault();

  var form = document.getElementById(formId);

  var url = new URL(form.action);
  url.searchParams.set('page', page);
  document.getElementById('pageValue').value = page;

  form.action = url;

  form.submit();
}

function updateColumnSort(formId, sortField) {
  var form = document.getElementById(formId);
  var url = new URL(form.action, window.location.origin);

  // 정렬 업데이트
  if (sortField !== null) {
    var currentSort = document.getElementById('sortValue').value;
    var currentOrder = document.getElementById('orderValue').value || 'ASC';
    var newOrder = (currentSort === sortField && currentOrder === 'ASC') ? 'DESC' : 'ASC';

    document.getElementById('sortValue').value = sortField;
    document.getElementById('orderValue').value = newOrder;
  }

  form.submit();
}


