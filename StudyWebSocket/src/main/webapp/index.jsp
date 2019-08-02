<!DOCTYPE html>
<html lang="en">
<head>
    <title>three.js webgl - loaders - Draco loader</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, user-scalable=no, minimum-scale=1.0, maximum-scale=1.0">
    <%--<script src="./js/three.module.js"></script>--%>
    <%--<script src="./js/OrbitControls.js"></script>--%>
    <%--<script src="./js/GLTFLoader.js"></script>--%>
    <%--<script src="../../libs/examples/js/libs/stats.min.js"></script>--%>
    <%--<script src="../../libs/examples/js/controls/OrbitControls.js"></script>--%>
    <%--<script src="../../libs/examples/js/loaders/GLTFLoader.js"></script>--%>
</head>

<body>
<div id="container"></div>
<%--<div id="info">--%>
    <%--<a href="http://threejs.org" target="_blank" rel="noopener">three.js</a> ---%>
    <%--<a href="https://github.com/google/draco" target="_blank" rel="noopener">DRACO</a> loader--%>
<%--</div>--%>
<script TYPE="module">


    import * as THREE from './threejs/build/three.module.js';
    import { OrbitControls } from './threejs/examples/jsm/controls/OrbitControls.js';
    import { GLTFLoader } from './threejs/examples/jsm/loaders/GLTFLoader.js';
    import { DRACOLoader } from './threejs/examples/jsm/loaders/DRACOLoader.js';
    import { EquirectangularToCubeGenerator } from './js/EquirectangularToCubeGenerator.js';
    import { PMREMGenerator } from './js/PMREMGenerator.js';
    import { PMREMCubeUVPacker } from './js/PMREMCubeUVPacker.js';

    var camera, scene, renderer,stats;

    var container = document.querySelector( '#container' );

    var gltfLoader = new GLTFLoader();
    init();
    animate();

    function init() {
        scene = new THREE.Scene();
        scene.background = new THREE.Color( 0xffffff);
        // scene.add(new THREE.AmbientLight(0xffffff));
        camera = new THREE.PerspectiveCamera( 75, window.innerWidth / window.innerHeight, 1, 1000 );
        camera.position.set(50, 50, 10);
        camera.lookAt(new THREE.Vector3(0, 0, 0));


        // scene.add(camera);

        // var cubeGenerator = new EquirectangularToCubeGenerator( texture, { resolution: 1024 } );
        // cubeGenerator.update( renderer );
        //
        // var pmremGenerator = new PMREMGenerator( cubeGenerator.renderTarget.texture );
        // pmremGenerator.update( renderer );
        //
        // var pmremCubeUVPacker = new PMREMCubeUVPacker( pmremGenerator.cubeLods );
        // pmremCubeUVPacker.update( renderer );
        //
        // var envMap = pmremCubeUVPacker.CubeUVRenderTarget.texture;
        DRACOLoader.setDecoderPath( './js/gltf/' );
        gltfLoader.setDRACOLoader( new DRACOLoader());
        const manager = new THREE.LoadingManager();

        // Intercept and override relative URLs.
        manager.setURLModifier((url, path) => {

            const normalizedURL = rootPath + url
                .replace(baseURL, '')
                .replace(/^(\.?\/)/, '');

            if (assetMap.has(normalizedURL)) {
                const blob = assetMap.get(normalizedURL);
                const blobURL = URL.createObjectURL(blob);
                blobURLs.push(blobURL);
                return blobURL;
            }

            return (path || '') + url;

        });

        gltfLoader.load(
            './zt.gltf',
            function (gltf) {
                console.log(gltf.scene);
                // gltf.scene.traverse( function ( child ) {
                //     if ( child.isMesh ) {
                //         child.material.envMap = envMap;
                //     }
                // } );
                // scene.add(gltf.mesh);
                // gltf.scene.traverse( function ( child ) {
                //     // console.log(child);
                //     if ( child.isMesh ) {
                //         // child.material.envMap = envMap;
                //     }
                // } );
                scene.add(gltf.scene||gltf.scene[0]);
            },

            function (error) {
                console.log(error);
            }
        );

        renderer = new THREE.WebGLRenderer();
        renderer.setSize(window.innerWidth, window.innerHeight);
        renderer.physicallyCorrectLights = true;
        renderer.gammaOutput = true;
        renderer.gammaFactor = 2.2;
        renderer.setClearColor( 0xcccccc );
        renderer.setPixelRatio( window.devicePixelRatio );

        const controls = new OrbitControls( camera, renderer.domElement );
        controls.autoRotate = false;
        controls.autoRotateSpeed = -10;
        controls.screenSpacePanning = true;
        container.appendChild(renderer.domElement);


    }

    function animate() {
        requestAnimationFrame(animate);
        renderer.render(scene,camera);
    }


    // function onWindowResize() {
    //
    //     camera.aspect = window.innerWidth / window.innerHeight;
    //     camera.updateProjectionMatrix();
    //
    //     renderer.setSize( window.innerWidth, window.innerHeight );
    //
    // }

    // function animate() {
    //
    //     render();
    //     requestAnimationFrame( animate );
    //
    // }
    //
    // function render() {
    //
    //     var timer = Date.now() * 0.0003;
    //
    //     camera.position.x = Math.sin( timer ) * 0.5;
    //     camera.position.z = Math.cos( timer ) * 0.5;
    //     camera.lookAt( 0, 0.1, 0 );
    //
    //     renderer.render( scene, camera );
    //
    // }
</script>
</body>
</html>
