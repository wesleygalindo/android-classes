var Stubby = require('stubby').Stubby;
var data = require('./api-stub.json');

var stubbyServer = new Stubby();

stubbyServer.start({
  stubs: 8882,
  admin: 8889,
  quiet: false,
  watch: 'api-stub.json',
  data: data
});