var
  PLATFORM_NAME = '',
  MAP_URL = '//api.map.baidu.com/',
  // SERVICE_URL = 'http://124.223.33.228:9527/', //后台服务地址
  SERVICE_URL = 'api/', //后台服务地址
  MAP_ZOOM = 12, 
  MAP_CENTER = [-4.10408,55.778253],
  MAP_KEY = 'kIIX4uTEmKQsQzIEI87DQgjyBd8gQnY0'
document.write('<script type="text/javascript" src="' + MAP_URL + 'api?type=webgl&v=1.0&ak=' + MAP_KEY + '"></script>');
