function getFormData($form){
    var unindexed_array = $form.serializeArray();

    var indexed_array = {};

    $.map(unindexed_array, function(n, i){
        indexed_array[n['name']] = n['value'];
    });

    return indexed_array;
}

function refreshUsers(viewModel){
    var r = jsRoutes.controllers.UserRestController.read();

    $.getJSON(r.url, function( data ) {
      viewModel.users(data)
    })
}

function createUser(newUser, viewModel){
    var r = jsRoutes.controllers.UserRestController.create();

    $.ajax({
      url:r.url,
      type:r.type,
      data:JSON.stringify(newUser),
      contentType:"application/json; charset=utf-8",
      dataType:"json",
      success: function(data){
        viewModel.users(data)
      }
    })
}

$(function() {
  // Initialize model
  var viewModel = {
    users : ko.observableArray()
  }

  refreshUsers(viewModel);

  viewModel.submit = function(model, event) {
    var $form = $(event.target).closest('form');

    createUser(getFormData($form), viewModel);
  }

  ko.applyBindings(viewModel);
});