import React from 'react';

class Map extends React.Component {
    mapRef = React.createRef();

    state = {
        map: null
    };

    componentDidMount() {
        const H = window.H;
        const platform = new H.service.Platform({
            apikey: "_avtU1QAAd7wlen69G44690--o6AVuc_kiN9O-qwGe0"
        });

        const defaultLayers = platform.createDefaultLayers();

        // Create an instance of the map
        const map = new H.Map(
            this.mapRef.current,
            defaultLayers.vector.normal.map,
            {
                // This map is centered over Europe
                center: { lat: 50, lng: 5 },
                zoom: 4,
                pixelRatio: window.devicePixelRatio || 1
            }
        );

        // MapEvents enables the event system
        // Behavior implements default interactions for pan/zoom (also on mobile touch environments)
        // This variable is unused and is present for explanatory purposes
        const behavior = new H.mapevents.Behavior(new H.mapevents.MapEvents(map));

        // Create the default UI components to allow the user to interact with them
        // This variable is unused
        const ui = H.ui.UI.createDefault(map, defaultLayers);

        
        // TEST: add marker
        var group = new H.map.Group();
        map.addObject(group);
        
        group.addEventListener('tap', function (e) {
            var bubble = new H.ui.InfoBubble(e.target.getGeometry(), {
                content: e.target.getData()
            });
            ui.addBubble(bubble);
        }, false);
        
        var coords = {lat: 45.00, lng: 0.00};
        
        var marker = new H.map.Marker(coords);
        marker.setData('<div><a href="https://www.liverpoolfc.tv">Liverpool</a></div>' +
        '<div>Anfield<br />Capacity: 54,074</div>');
        group.addObject(marker);
        
        this.setState({ map });
    }


    componentWillUnmount() {
        // Cleanup after the map to avoid memory leaks when this component exits the page
        this.state.map.dispose();
    }

    render() {
        return (
            // Set a height on the map so it will display
            <div ref={this.mapRef} style={{ height: "500px" }} />
        );
    }
}

export default Map;