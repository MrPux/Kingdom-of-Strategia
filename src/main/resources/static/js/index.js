// Wait for the DOM to load
window.addEventListener('load', () => {
    // Get references to various elements
    const map = document.getElementById('map');
    const button = document.getElementById("village-action-btn");
    const villageImgs = document.querySelectorAll(".village-img");
    const tooltip = document.getElementById("village-tooltip");
    const tooltipText = document.getElementById("tooltip-text");
    const wrapper = document.getElementById('map-wrapper');
    const spyglass = document.getElementById('spyglass-overlay');

    // Initialize zoom variables
    let zoomedIn = false;
    const zoomFactor = 4.8;
    let zoomOrigin = { x: 0, y: 0 };
    let hideButtonTimeout; // Timeout to hide the button

    const initialScroll = {
        left: wrapper.scrollLeft,
        top: wrapper.scrollTop
    };

    // Button hover effects
    button.addEventListener("mouseenter", () => {
        // This event listener is triggered when the mouse enters the button area.
        // State change: Change background color and text color on mouse enter
        button.style.backgroundColor = "rgba(0, 102, 110, 0.517)"; // Change the background color.
        button.style.color = "white"; // Change the text color.
    });

    button.addEventListener("mouseleave", () => {
        // This event listener is triggered when the mouse leaves the button area.
        // State change: Change background color, text color, and hide the button on mouse leave
        button.style.backgroundColor = "rgba(66, 66, 66, 0.517)"; // Change the background color.
        button.style.color = "white"; // Change the text color.
        button.style.display = "none"; // Hide the button.
    });

    // Village image hover effects
    villageImgs.forEach(img => {
        img.addEventListener("mouseenter", () => {
            // This event listener is triggered when the mouse enters a village image area.
            clearTimeout(hideButtonTimeout); // Clear any existing timeout to hide the button.
            const rect = img.getBoundingClientRect(); // Get the bounding rectangle of the image.
            const mapRect = map.getBoundingClientRect(); // Get the bounding rectangle of the map.

            const left = rect.left - wrapper.getBoundingClientRect().left + img.offsetWidth / 2;
            const top = rect.top - wrapper.getBoundingClientRect().top - 10;
            const btnLeft = left - 25;
            const btnTop = top + img.offsetHeight + 10;

            const src = img.getAttribute("src");
            const fileName = src.split("/").pop().replace(".png", "");
            const formatted = fileName.replace(/[-_]/g, " ").replace(/\b\w/g, c => c.toUpperCase());

            const population = img.dataset.population;
            const structures = img.dataset.structures;
            const x = img.dataset.x;
            const y = img.dataset.y;
            const villageId = img.dataset.villageId;

            button.dataset.villageId = villageId;
            button.dataset.population = population;
            button.dataset.structures = structures;
            button.dataset.name = formatted;


            // Attach village ID to the button
            button.setAttribute("data-village-id", villageId); // Set the village ID as a data attribute on the button.

            tooltipText.innerHTML = `
                <strong>${formatted}</strong><br>
                <em>ID: ${villageId}</em><br>
                Population: ${population}<br>
                Structures: ${structures}<br>
                Coordinates: (${x}, ${y})
            `;

            // State change: Display the tooltip and the button
            tooltip.style.left = `${left}px`; // Set the left position of the tooltip.
            tooltip.style.top = `${top}px`; // Set the top position of the tooltip.
            tooltip.style.display = "block"; // Show the tooltip.

            button.style.left = `${btnLeft}px`; // Set the left position of the button.
            button.style.top = `${btnTop}px`; // Set the top position of the button.
            button.style.display = "block"; // Show the button.
        });

        img.addEventListener("mouseleave", () => {
            // This event listener is triggered when the mouse leaves a village image area.
            // State change: Hide the tooltip
            tooltip.style.display = "none"; // Hide the tooltip.
            hideButtonTimeout = setTimeout(() => {
                if (!button.matches(":hover")) {
                    // State change: Hide the button after a delay if not hovered
                    button.style.display = "none"; // Hide the button.
                }
            }, 3000); // 3 seconds delay
        });

    });

    // Enemy image hover effects
    const enemyImgs = document.querySelectorAll(".enemy-img"); // Select all enemy images.
    const enemyTooltip = document.getElementById("enemy-tooltip"); // Get the enemy tooltip element.
    const enemyTooltipText = document.getElementById("enemy-tooltip-text"); // Get the enemy tooltip text element.

    enemyImgs.forEach(enemy => {
        enemy.addEventListener("mouseenter", () => {
            // This event listener is triggered when the mouse enters an enemy image area.
            const level = enemy.dataset.level; // Get the level of the enemy.
            const rect = enemy.getBoundingClientRect(); // Get the bounding rectangle of the enemy image.
            const mapRect = map.getBoundingClientRect(); // Get the bounding rectangle of the map.

            const left = rect.left - mapRect.left + enemy.offsetWidth / 2;
            const top = rect.top - mapRect.top - 10;

            enemyTooltipText.textContent = `Lvl ${level}`; // Set the text content of the enemy tooltip.
            // State change: Show the enemy tooltip
            enemyTooltip.style.left = `${left}px`; // Set the left position of the tooltip.
            enemyTooltip.style.top = `${top}px`; // Set the top position of the tooltip.
            enemyTooltip.style.display = "block"; // Show the tooltip.
        });

        enemy.addEventListener("mouseleave", () => {
            // This event listener is triggered when the mouse leaves an enemy image area.
            // State change: Hide the enemy tooltip
            enemyTooltip.style.display = "none"; // Hide the tooltip.
        });
    });


    button.addEventListener("mouseleave", () => {
        hideButtonTimeout = setTimeout(() => {
            // State change: Hide the button after a delay
            button.style.display = "none";
        }, 3000); // match the same 3-second delay
    });


    // Button click event to redirect to village page
    button.addEventListener("click", () => {
        // This event listener is triggered when the button is clicked.
        const villageId = button.dataset.villageId; // Get the village ID from the button's data attribute.
        console.log("Redirecting to village ID:", villageId); // Log the village ID to the console.
        // State change: Redirect to the village page
        window.location.href = "/village/" + villageId; // Redirect the user to the village page.
    });


    // Function to update spyglass position
    function updateSpyglassPosition(x, y) {
        // This function updates the position of the spyglass overlay based on the given coordinates.
        const wrapperRect = wrapper.getBoundingClientRect(); // Get the bounding rectangle of the map wrapper.
        const mapRect = map.getBoundingClientRect(); // Get the bounding rectangle of the map.
        const screenX = x - wrapper.scrollLeft + mapRect.left; // Calculate the X coordinate of the spyglass position relative to the screen.
        const screenY = y - wrapper.scrollTop + mapRect.top; // Calculate the Y coordinate of the spyglass position relative to the screen.

        spyglass.style.left = `${screenX - 60}px`; // Set the left position of the spyglass.
        spyglass.style.top = `${screenY - 10}px`; // Set the top position of the spyglass.
        spyglass.style.width = '65px'; // Set the width of the spyglass.
        spyglass.style.height = 'auto'; // Set the height of the spyglass.
        spyglass.style.transform = `scale(${zoomFactor})`; // Set the scale of the spyglass.
    }

    // Double click event to zoom in and out
    map.addEventListener('dblclick', (e) => {
        // This event listener is triggered when the map is double-clicked.
        const rect = map.getBoundingClientRect(); // Get the bounding rectangle of the map.
        const x = e.clientX - rect.left + wrapper.scrollLeft; // Calculate the X coordinate of the double-click relative to the map.
        const y = e.clientY - rect.top + wrapper.scrollTop; // Calculate the Y coordinate of the double-click relative to the map.

        // State change: Toggle zoomedIn state
        zoomedIn = !zoomedIn; // Toggle the zoomedIn state.

        document.addEventListener('mousemove', (e) => {
            // This event listener is triggered when the mouse moves over the document.
            if (zoomedIn) {
                // If the map is zoomed in:
                const offsetX = 60; // Define an X offset for the spyglass position.
                const offsetY = 60; // Define a Y offset for the spyglass position.
                // State change: Update spyglass position on mousemove when zoomed in
                spyglass.style.left = `${e.clientX - offsetX}px`; // Update the left position of the spyglass based on the mouse's X coordinate.
                spyglass.style.top = `${e.clientY - offsetY}px`; // Update the top position of the spyglass based on the mouse's Y coordinate.
            }
        });

        if (zoomedIn) {
            // If the map is zoomed in:
            zoomOrigin = { x, y }; // Set the zoom origin to the coordinates of the double-click.
            // State change: Zoom in the map and show the spyglass
            map.style.transformOrigin = `${x}px ${y}px`; // Set the transform origin of the map to the double-click coordinates.
            map.style.transform = `scale(${zoomFactor})`; // Scale the map by the zoom factor.
            spyglass.style.opacity = '1'; // Show the spyglass.
            updateSpyglassPosition(x, y); // Update the position of the spyglass.
        } else {
            // If the map is zoomed out:
            map.style.transformOrigin = `${zoomOrigin.x}px ${zoomOrigin.y}px`; // Reset the transform origin of the map to the original zoom origin.
            // State change: Zoom out the map and hide the spyglass
            map.style.transform = `scale(1)`; // Reset the scale of the map to 1 (original size).

            setTimeout(() => {
                // After a short delay:
                wrapper.scrollTo({
                    left: initialScroll.left,
                    top: initialScroll.top,
                    behavior: 'smooth'
                });
                spyglass.style.opacity = '0'; // Hide the spyglass.
            }, 900);
        }
    });

    // Scroll event to update spyglass position
    wrapper.addEventListener('scroll', () => {
        // This event listener is triggered when the map wrapper is scrolled.
        if (zoomedIn) {
            // If the map is zoomed in:
            const rect = map.getBoundingClientRect(); // Get the bounding rectangle of the map.
            const x = zoomOrigin.x + wrapper.scrollLeft - rect.left; // Calculate the X coordinate of the zoom origin relative to the map.
            const y = zoomOrigin.y + wrapper.scrollTop - rect.top; // Calculate the Y coordinate of the zoom origin relative to the map.
            updateSpyglassPosition(x, y); // Update the position of the spyglass.
        }
    });

    // Prevent default gesture and wheel events
    document.addEventListener('gesturestart', (e) => e.preventDefault()); // Prevent default gesture events.
    document.addEventListener('wheel', (e) => {
        // This event listener is triggered when the mouse wheel is scrolled.
        if (e.ctrlKey) e.preventDefault(); // Prevent default wheel events when the control key is pressed.
    }, { passive: false });
});
