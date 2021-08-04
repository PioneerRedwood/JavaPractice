<!doctype html>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <title>jQuery Panzoom Plugin Examples</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <style type="text/css">
      body { background: #F5FCFF; color: #333666; }
      section { text-align: center; margin: 150px 0 50px 0; }
      .panzoom-parent { border: 2px solid #333; }
      .panzoom-parent .panzoom { border: 2px dashed #666; }
      .buttons { margin: 40px 0 0; }
    </style>
    <script src="panzoom.js"></script>
  </head>
	<body>
		<section>
			<h1>Panning and Zooming Example</h1>
			<div class="parent">
				<div class="panzoom" id="panzoom-element">
					<img src="../static/awesome_tiger.jpg">
				</div>
			</div>
			<div class="buttons">
				<button id="zoom-in">Zoom In</button>
				<button id="zoom-out">Zoom Out</button>
				<button id="reset">Reset</button>
			</div> 
			<script>
				const elem = document.getElementById('panzoom-element');
				const zoomInButton = document.getElementById('zoom-in');
				const zoomOutButton = document.getElementById('zoom-out');
				const resetButton = document.getElementById('reset');
				const panzoom = Panzoom(elem);
				const parent = elem.parentElement;
				// No function bind needed
				parent.addEventListener('wheel', panzoom.zoomWithWheel);
				zoomInButton.addEventListener('click', panzoom.zoomIn);
				zoomOutButton.addEventListener('click', panzoom.zoomOut);
				resetButton.addEventListener('click', panzoom.reset);
			</script>
		</section>
  </body>
</html>
