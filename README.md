# Level Editor for Platformer Games

<img src="https://i.ibb.co/gvwwhBb/Editor.png" alt="Screenshot" width="100%">

## Table of Contents

- [Description](#description)
- [Features](#features)
- [Pixel image encoding](#pixel-image-encoding)
- [Usage](#usage)
- [Integration into games](#integration-into-games)
- [Patterns used](#patterns-used)
- [Dependencies](#dependencies)
- [Contributing](#contributing)
- [License](#license)

## Description

This tool empowers game developers and designers to create, edit, and manage levels for platformer games in an intuitive and efficient manner. Below, you will find information about the features, usage, and technical aspects of the project. <br>
Whether you're a game developer or a hobbyist, this editor simplifies the level design process by offering a user-friendly interface and innovative features.

## Features

### 1. Project and Level Management
- Create and manage multiple projects.
- Create and edit multiple levels within each project.
- Serialize and deserialize project and level data.
### 2. Tabbed Interface
- Open projects with levels displayed as separate tabs.
- Each tab represents a distinct level within the project.
### 3. Tile Set Integration
- Utilize existing tile sets for level creation.
- Import custom tile sets for a personalized experience.
### 4. Layered Object Placement
- Add various objects to levels on multiple layers.
- Layers include solid tiles, decorations, objects, and enemies.
### 5. Export Options
- Save levels as images for visual reference.
- Export levels as pixel images for integration into games.

## Pixel image encoding

One innovative approach offered by this editor is the ability to represent levels as pixel images. Each pixel's RGB values encode specific elements or features of the level. This method simplifies the level design process and allows for easy integration into games.

<img src="https://i.ibb.co/hfSJw6P/Pixel.png" alt="PixelImg" width="100%">

For instance, you can assign RGB values to different objects or terrain types:

- Red (value, 255, 255) could represent solid tiles.
- White (255, 255, 255) could represent empty space.
- Green (255, value, 255) could represent enemies.
- Blue (255, 255, value) could represent objects.

This approach simplifies level creation by allowing developers to utilize graphic software for designing levels, then effortlessly integrating them into games as images. This technique is particularly beneficial for less intricate games or those with a vintage vibe, highlighting manual level design.

## Usage

### Installation
- Clone this repository.
- Ensure you have Java Development Kit (JDK 10.0.2) installed.
- Install the required dependencies (see [Dependencies](#dependencies)).
- Compile the game using your preferred Java IDE or command line.
- Run the compiled game executable.
### Creating Projects
- Launch the editor.
- Create a new project.
- Start adding levels to it.
### Level Design 
- Choose tiles from existing tile sets or import custom ones for diverse designs.
- Select tile in bottom panel, select layer and click 'Add' button to place tile.
### Serialization
- Serialize your projects and levels for easy sharing or storage.
### Pixel Image Import/Export
For game integration, design your level and export it as a pixel image. The RGB values of each pixel encode the corresponding elements. Use the predefined RGB values to interpret the pixel information correctly.

## Integration into games

To integrate the pixel images created in the Level Editor into your game, follow these steps:

1. **Generate Pixel Images**: Use the Level Editor to design and save your game levels as pixel images. These images are composed of two sections: the left section contains information about solid tiles, objects, and enemies, while the right section holds decorations. <br> <img src="https://i.ibb.co/j44P1Lw/PixelImg.png" alt="PixelImg" width="100%">

2. **Solid Tiles, Objects, and Enemies**:
   - In the left section of the pixel image, solid tiles, objects, and enemies are represented.
   - Solid tiles can be found on either layer 3 or layer 5.
   - If they are on layer 3, only the RED value serves as the ID of tile in the RGB format, while G and B are both set to 254.
   - If solid tiles are on layer 5, similarly, the RED value represents the ID of tile, but G and B are both set to 255.
   - For enemies, the GREEN value is utilized, with RED and BLUE set to 254.
   - Objects are identified using the BLUE value, with RED and GREEN set to 254.
   - In your game's code, extract the pixel values of the image's left section to recreate solid tiles, objects, and enemies in the respective layers and positions.

3. **Decorations**:
   - The right section of the pixel image contains decoration data.
   - Decorations are placed on layers 0, 1, 2, or 4.
   - The GREEN value determines the decoration layer, while the BLUE value signifies the decoration's ID.
   - Extract the pixel values from the image's right section in your game to accurately position and render the decorations.

4. **Reading Pixel Data**:
   - Utilize your game's image processing or pixel reading capabilities to interpret the RGB values of the pixel image.
   - Use the provided RGB mappings to extract information about tiles, objects, enemies, and decorations.
   - Implement the logic to place these elements in your game's level based on the pixel data.
  
## Patterns used

The project incorporates various design patterns to ensure stability and maintainability, including:
- Singleton (Thread safe)
- Observer
- Composite
- Abstract Factory
- State
- Command
- Strategy
- Combinator

## Dependencies

- Java Swing
- GSON Serializer

## Contributing

Contributions to this project are welcome! Feel free to submit bug reports, feature requests, or pull requests. Let's collaborate to make this Level Editor even better.

## License

This project is licensed under the [MIT License](LICENSE).
