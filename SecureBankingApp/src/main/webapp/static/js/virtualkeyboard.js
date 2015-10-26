// Load the Google Onscreen Keyboard API
      google.load("elements", "1", {
          packages: "keyboard"
      });

      function onLoad() {
        var kbd = new google.elements.keyboard.Keyboard(
          [google.elements.keyboard.LayoutCode.ENGLISH]);
      }

      google.setOnLoadCallback(onLoad);